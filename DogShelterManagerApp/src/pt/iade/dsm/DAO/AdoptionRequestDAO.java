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

// TODO: Auto-generated Javadoc
/**
 * The Class AdoptionRequestDAO.
 */
public class AdoptionRequestDAO {
	
	/**
	 * Insert adoption request DB.
	 *
	 * @param adoption the adoption
	 * @throws SQLException the SQL exception
	 */
	public static void insertAdoptionRequestDB(Adoption adoption) throws SQLException {
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
            preparedStatement.setString(3, adoption.getState());
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
	 * Load adoption requests.
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
				resultSet = statement.executeQuery("SELECT * FROM AdoptionRequests");
	         while (resultSet.next())
	         {
	             Adoption adoption = new Adoption(resultSet.getString("guestID"),
	            		 							resultSet.getInt("dogID"),
	            		 							resultSet.getString("state"));
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
	 * Load adoption requests on hold.
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
				resultSet = statement.executeQuery("SELECT * FROM AdoptionRequests where state='on hold'");
	         while (resultSet.next())
	         {
	             Adoption adoption = new Adoption(resultSet.getString("guestID"),
	            		 							resultSet.getInt("dogID"),
	            		 							resultSet.getString("state"));
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
	 * Decision upload.
	 *
	 * @param adoption the adoption
	 * @throws SQLException the SQL exception
	 */
	public static void decisionUpload (Adoption adoption) throws SQLException {
	
	Connection conn = DBConnector.getConnection();
    PreparedStatement preparedStatement = null;
    
    try
    {
        
        //2. Create a String that holds the query with ? as user inputs
        String sql = "UPDATE AdoptionRequests SET state=? WHERE adoptionID=?;";
                
        //3. prepare the query
        preparedStatement = conn.prepareStatement(sql);
        
        
        preparedStatement.setString(1, adoption.getState());
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
