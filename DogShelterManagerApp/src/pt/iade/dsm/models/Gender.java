package pt.iade.dsm.models;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.DAO.DBConnector;

/**
 * The Class Gender.
 */
public class Gender {
	
	
	
	/** The gender ID. */
	private int genderID;
	
	/** The gender. */
	private String gender;
	



	/**
	 * Gets the genders.
	 *
	 * @return the genders
	 */
	public static ObservableList<Gender> getGenders() {
		ObservableList<Gender> genders =
				FXCollections.observableArrayList();
		
		Connection conn =  DBConnector.getConnection();
		
		String sql = "SELECT * FROM Gender";
		try (PreparedStatement stat = conn.prepareStatement(sql)){
			try(ResultSet rs = stat.executeQuery()) {
				while (rs.next()) {
				genders.add(new Gender((rs.getInt("genderID")),rs.getString("gender")));
					 
				
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return genders;
	}
	
	
	/**
	 * Instantiates a new gender.
	 *
	 * @param genderID the gender ID
	 * @param gender the gender
	 */
	public Gender(int genderID,String gender) {
		this.genderID = genderID;
		this.gender = gender;
	}

	/**
	 * Gets the gender ID.
	 *
	 * @return the gender ID
	 */
	public int getGenderID() {
		return genderID;
	}

	/**
	 * Sets the gender ID.
	 *
	 * @param genderID the new gender ID
	 */
	public void setGenderID(int genderID) {
		this.genderID = genderID;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString(){
		return this.gender;
	}

}

