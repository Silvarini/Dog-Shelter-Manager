package pt.iade.dsm.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.DAO.DBConnector;

/**
 * This model represents the adoption state, where you have the constructor and DAO.
 */
public class StateAdoption {

	/** The adoption ID. */
	private int adoptionID;
	
	/** The adoption. */
	private String adoption;

	

	/**
	 * Load adoption states.
	 *
	 * @return the observable list
	 */
	public static ObservableList<StateAdoption> loadAdoptionStates() {
		ObservableList<StateAdoption> adoptionStates =
				FXCollections.observableArrayList();
		
		Connection conn =  DBConnector.getConnection();
		
		String sql = "SELECT * FROM StateAdoption";
		try (PreparedStatement stat = conn.prepareStatement(sql)){
			try(ResultSet rs = stat.executeQuery()) {
				while (rs.next()) {
				adoptionStates.add(new StateAdoption((rs.getInt("stateTypesID")),rs.getString("stateTypes")));
					 
				
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return adoptionStates;
	}
	
	

	/**
	 * Gets the decisions.
	 *
	 * @return the decisions
	 */
	public static ObservableList<StateAdoption> getDecisions() {
		ObservableList<StateAdoption> decisions =
				FXCollections.observableArrayList();
		
		Connection conn =  DBConnector.getConnection();
		
		String sql = "SELECT * FROM StateAdoption WHERE stateTypesID > 1";
		try (PreparedStatement stat = conn.prepareStatement(sql)){
			try(ResultSet rs = stat.executeQuery()) {
				while (rs.next()) {
				decisions.add(new StateAdoption(rs.getInt("stateTypesID"),rs.getString("stateTypes")));
					 
				
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return decisions;
	}
	
	
	
	
	/**
	 * Instantiates a new state adoption.
	 *
	 * @param adoptionID the adoption ID
	 * @param adoption the adoption
	 */
	public StateAdoption(int adoptionID, String adoption) {
		this.adoptionID = adoptionID;
		this.adoption = adoption;
	}
	
	

	/**
	 * Gets the adoption ID.
	 *
	 * @return the adoption ID
	 */
	public int getAdoptionID() {
		return adoptionID;
	}

	/**
	 * Sets the adoption ID.
	 *
	 * @param adoptionID the new adoption ID
	 */
	public void setAdoptionID(int adoptionID) {
		this.adoptionID = adoptionID;
	}

	/**
	 * Gets the adoption.
	 *
	 * @return the adoption
	 */
	public String getAdoption() {
		return adoption;
	}

	/**
	 * Sets the adoption.
	 *
	 * @param adoption the new adoption
	 */
	public void setAdoption(String adoption) {
		this.adoption = adoption;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString(){
		return this.adoption;
	}

	
	

}
