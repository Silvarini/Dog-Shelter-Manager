package pt.iade.dsm.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.models.Adoption;
import pt.iade.dsm.models.StateAdoption;

/**
 * This class connects to the database getting and setting values for the adoption requests.
 */
public class AdoptionRequestDAO {
	
	/**
	 * Insert adoption request values into the database.
	 *
	 * @param adoption the adoption
	 * @param adoptionState the adoption state
	 * @throws SQLException the SQL exception
	 */
	public static void insertAdoptionRequestDB(Adoption adoption, StateAdoption adoptionState) throws SQLException {
		Connection conn = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        
        try
        {
            
            //2. Create a String that holds the query with ? as user inputs
            String sql = "INSERT INTO AdoptionRequests(guestID, dogID, state, requestDate)"
                    + "VALUES (?,?,?,?)";
                    
            //3. prepare the query
            preparedStatement = conn.prepareStatement(sql);
            
            //4.convert local date into sql date
            Date requestDate = Date.valueOf(adoption.getRequestDate());
        
            
            //5. Bind the values to the parameters
            preparedStatement.setString(1, adoption.getGuestID());
            preparedStatement.setInt(2, adoption.getDogID());
            preparedStatement.setInt(3, adoptionState.getAdoptionID());
            preparedStatement.setDate(4, requestDate);
            
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (preparedStatement != null)
                preparedStatement.close();
            
            if (conn != null)
                conn.close();
        }
		
	}

	/**
	 * Load adoption requests from the database.
	 *
	 * @return the observable list
	 * @throws SQLException the SQL exception
	 */
	public static ObservableList<Adoption> loadAdoptionRequests() throws SQLException {
		 Connection conn = DBConnector.getConnection();
	     Statement statement = null;
	     ResultSet resultSet = null;
	     ObservableList<Adoption> adoptions = FXCollections.observableArrayList(); 
	     try{
				//create a statement object
				statement = conn.createStatement();

				//create the SQL query
				resultSet = statement.executeQuery("SELECT adoptionID,requestDate, guestID, dogID, stateTypesID, stateTypes FROM AdoptionRequests, StateAdoption WHERE state = stateTypesID AND state = 2 OR state = 3");
	         while (resultSet.next())
	         {
	             Adoption adoption = new Adoption(resultSet.getString("guestID"),
	            		 							resultSet.getInt("dogID"),
	            		 							new StateAdoption(resultSet.getInt("stateTypesID"),resultSet.getString("stateTypes")));
	             adoption.setAdoptionID(resultSet.getInt("adoptionID"));
	             adoption.setRequestDate(resultSet.getDate("requestDate").toLocalDate());
	             adoptions.add(adoption);
	             
	         } 
	         }
	     catch (SQLException e)
	     {
	         System.err.println(e.getMessage());
	     }
		return adoptions;
	
}
	
	/**
	 * Load adoption requests, where the requested's state is " on hold ".
	 *
	 * @return the observable list
	 * @throws SQLException the SQL exception
	 */
	public static ObservableList<Adoption> loadAdoptionRequestsOnHold() throws SQLException {
		 Connection conn = DBConnector.getConnection();
	     Statement statement = null;
	     ResultSet resultSet = null;
	     ObservableList<Adoption> adoptions = FXCollections.observableArrayList(); 
	     try{
				//create a statement object
				statement = conn.createStatement();

				//create the SQL query
				resultSet = statement.executeQuery("SELECT adoptionID, requestDate,guestID, dogID, stateTypesID, stateTypes FROM AdoptionRequests,StateAdoption where state = 1 AND stateTypesID = state");
	         while (resultSet.next())
	         {
	             Adoption adoption = new Adoption(resultSet.getString("guestID"),
	            		 							resultSet.getInt("dogID"),
	            		 							new StateAdoption(resultSet.getInt("stateTypesID"),resultSet.getString("stateTypes")));
	             adoption.setAdoptionID(resultSet.getInt("adoptionID"));
	             adoption.setRequestDate(resultSet.getDate("requestDate").toLocalDate());
	             adoptions.add(adoption);
	             
	         } 
	         }
	     catch (SQLException e)
	     {
	         System.err.println(e.getMessage());
	     }
		return adoptions;
	
}
	
	/**
	 * Updates the adoption request's state from the employee's decision.
	 *
	 * @param adoption the adoption
	 * @param adoptionState the adoption state
	 * @throws SQLException the SQL exception
	 */
	public static void decisionUpload (Adoption adoption, StateAdoption adoptionState) throws SQLException {
	
	Connection conn = DBConnector.getConnection();
    PreparedStatement preparedStatement = null;
    
    try
    {
        
        //2. Create a String that holds the query with ? as user inputs
        String sql = "UPDATE AdoptionRequests SET state=? WHERE adoptionID=?;";
                
        //3. prepare the query
        preparedStatement = conn.prepareStatement(sql);
        
        
        preparedStatement.setInt(1, adoptionState.getAdoptionID());
        preparedStatement.setInt(2, adoption.getAdoptionID());
        
        preparedStatement.executeUpdate();
    }
    catch (Exception e)
    {
        System.err.println(e.getMessage());
    }
    finally
    {
        if (preparedStatement != null)
            preparedStatement.close();
        
        if (conn != null)
            conn.close();
    }
	
}
	
	

	
}
