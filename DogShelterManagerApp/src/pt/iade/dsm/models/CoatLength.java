package pt.iade.dsm.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.DAO.DBConnector;

/**
 * This model represents the coat length class, where you have the constructor and DAO.
 */
public class CoatLength {
	
	/** The coat ID. */
	private int coatID;
	
	/** The coat. */
	private String coat;
	
	
	
	

	/**
	 * Load coat lengths.
	 *
	 * @return the observable list
	 */
	public static ObservableList<CoatLength> loadCoatLengths() {
		ObservableList<CoatLength> coats =
				FXCollections.observableArrayList();
		
		Connection conn =  DBConnector.getConnection();
		
			String sql = "SELECT * FROM CoatLengthClass";
			try (PreparedStatement stat = conn.prepareStatement(sql)){
				try(ResultSet rs = stat.executeQuery()) {
					while (rs.next()) {
					coats.add(new CoatLength((rs.getInt("cLengthClassID")),rs.getString("cLengthClass")));
						 
					
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 

			return coats;
		}
		

	
/**
 * Verify coat length.
 *
 * @param coatDog the coat dog
 * @param coat the coat
 * @return the coat length
 * @throws SQLException the SQL exception
 */
public static CoatLength VerifyCoatLength(String coatDog, CoatLength coat) throws SQLException {
		
		Connection conn = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM CoatLengthClass AND cLengthClass = ?";
            
            //3.  prepare the statement
            preparedStatement = conn.prepareStatement(sql);
           
            //4.
            preparedStatement.setString(1, coatDog);
            
            
            //5. execute the query
            resultSet = preparedStatement.executeQuery();
            
            //6.  extract the password and role from the resultSet
            while (resultSet.next())
            {
            			 coat = new CoatLength(resultSet.getInt("cLengthClassID"),resultSet.getString("cLengthClass"));
            									
            }
                  
           
        } catch (Exception e)
        {
        	e.printStackTrace();
        }
        finally
        {
        	if (conn != null)
        		conn.close();
        	if(preparedStatement != null)
        		preparedStatement.close();
        	if(resultSet != null)
        		resultSet.close();
        }
		return coat;
}




	/**
	 * Instantiates a new coat length.
	 *
	 * @param coatID the coat ID
	 * @param coat the coat
	 */
	public CoatLength(int coatID, String coat) {
		this.coatID = coatID;
		this.coat = coat;
	}


	/**
	 * Gets the coat ID.
	 *
	 * @return the coat ID
	 */
	public int getCoatID() {
		return coatID;
	}


	/**
	 * Sets the coat ID.
	 *
	 * @param coatID the new coat ID
	 */
	public void setCoatID(int coatID) {
		this.coatID = coatID;
	}


	/**
	 * Gets the coat.
	 *
	 * @return the coat
	 */
	public String getCoat() {
		return coat;
	}


	/**
	 * Sets the coat.
	 *
	 * @param coat the new coat
	 */
	public void setCoat(String coat) {
		this.coat = coat;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString(){
		return this.coat;
	}

	
	
	

}
