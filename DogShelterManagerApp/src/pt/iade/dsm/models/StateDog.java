	package pt.iade.dsm.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.DAO.DBConnector;

/**
 * This model represents the dog's state, where you have the constructor and DAO.
 *  
 */
public class StateDog {
	
	/** The state ID. */
	private int stateID;
	
	/** The state. */
	private String state;

	


	/**
	 * Gets the state dog.
	 *
	 * @return the state dog
	 */
	public static ObservableList<StateDog> getStateDog() {
		ObservableList<StateDog> states =
				FXCollections.observableArrayList();
		
		Connection conn =  DBConnector.getConnection();
		
		String sql = "SELECT * FROM StateType";
		try (PreparedStatement stat = conn.prepareStatement(sql)){
			try(ResultSet rs = stat.executeQuery()) {
				while (rs.next()) {
				states.add(new StateDog((rs.getInt("stateID")),rs.getString("state")));
					 
				
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return states;
	}
	
	


	/**
	 * Gets the states house dogs.
	 *
	 * @return the states house dogs
	 */
	public static ObservableList<StateDog> getStatesHouseDogs() {
		ObservableList<StateDog> states =
				FXCollections.observableArrayList();
		
		Connection conn =  DBConnector.getConnection();
		
		String sql = "SELECT * FROM StateType WHERE stateID = 1 OR stateID = 3";
		try (PreparedStatement stat = conn.prepareStatement(sql)){
			try(ResultSet rs = stat.executeQuery()) {
				while (rs.next()) {
				states.add(new StateDog((rs.getInt("stateID")),rs.getString("state")));
					 
				
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return states;
	}
	
	
	
	
	
	/**
	 * Instantiates a new state dog.
	 *
	 * @param stateID the state ID
	 * @param state the state
	 */
	public StateDog(int stateID, String state) {
		this.stateID = stateID;
		this.state = state;
	}

	
	
	/**
	 * Gets the state ID.
	 *
	 * @return the state ID
	 */
	public int getStateID() {
		return stateID;
	}

	/**
	 * Sets the state ID.
	 *
	 * @param stateID the new state ID
	 */
	public void setStateID(int stateID) {
		this.stateID = stateID;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString(){
		return this.state;
	}

	
	
	
	
	
}
