package pt.iade.dsm.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import pt.iade.dsm.models.Guest;

/**
 * This class connects to the database to set and get values from the guest's table.
 */
public class GuestDAO {


	/**
	 * Insert guest into database.
	 *
	 * @param guest the guest
	 * @throws SQLException the SQL exception
	 */
	public static void insertGuestDB(Guest guest) throws SQLException {
		 	Connection conn = DBConnector.getConnection();
	        PreparedStatement preparedStatement = null;
	        
	        try
	        {
	            
	            //2. Create a String that holds the query with ? as user inputs
	            String sql = "INSERT INTO Guest (firstName, lastname, phone, email, adress, obs)"
	                    + "VALUES (?,?,?,?,?,?)";
	                    
	            //3. prepare the query
	            preparedStatement = conn.prepareStatement(sql);
	            
	                   
	            //5. Bind the values to the parameters
	            preparedStatement.setString(1, guest.getFirstName());
	            preparedStatement.setString(2, guest.getLastName());
	            preparedStatement.setString(3, guest.getPhone());
	            preparedStatement.setString(4, guest.getEmail());
	            preparedStatement.setString(5, guest.getAdress());
	            preparedStatement.setString(6, guest.getObs());
	            
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
	
	/**
	 * Load guest from database.
	 *
	 * @return the observable list
	 * @throws SQLException the SQL exception
	 */
	public static ObservableList<Guest> loadGuest() throws SQLException {
		 Connection conn = DBConnector.getConnection();
	     Statement statement = null;
	     ResultSet resultSet = null;
	     ObservableList<Guest> guests = FXCollections.observableArrayList(); 
	     try{
				//create a statement object
				statement = conn.createStatement();

				//create the SQL query
				resultSet = statement.executeQuery("SELECT * FROM Guest");
	         while (resultSet.next())
	         {
	             Guest guest = new Guest(resultSet.getString("firstName"), 
	            		 				 resultSet.getString("lastName"), 
	            		 				 resultSet.getString("phone"), 
	            		 				 resultSet.getString("email"),
	            		 				 resultSet.getString("adress"),
	            		 				 resultSet.getString("obs"));
	             guests.add(guest);
	             
	         } 
	         }
	     catch (SQLException e)
	     {
	         System.err.println(e.getMessage());
	     }
		return guests;	
	
	}
	
	/**
	 * Verifies guest from database.
	 *
	 * @param guestID the guest ID
	 * @return the guest
	 * @throws SQLException the SQL exception
	 */
public static Guest VerifyGuest(String guestID) throws SQLException {
		
		Connection conn = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM Guest WHERE phone = ?;";
            
            //3.  prepare the statement
            preparedStatement = conn.prepareStatement(sql);
           
            //4.
            preparedStatement.setString(1, guestID);
            
            
            //5. execute the query
            resultSet = preparedStatement.executeQuery();
            
            //6.  extract the password and role from the resultSet
            while (resultSet.next())
            {
            	  Guest guest = new Guest(resultSet.getString("firstName"), 
 		 				 resultSet.getString("lastName"), 
 		 				 resultSet.getString("phone"), 
 		 				 resultSet.getString("email"),
 		 				 resultSet.getString("adress"),
 		 				 resultSet.getString("obs"));

          		return guest;

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
	return null;

	}

	
	
}
