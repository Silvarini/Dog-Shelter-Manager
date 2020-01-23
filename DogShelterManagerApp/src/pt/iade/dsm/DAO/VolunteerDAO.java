package pt.iade.dsm.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.models.Registration;
import pt.iade.dsm.models.Volunteer;

/**
 * The Class VolunteerDAO.
 */
public class VolunteerDAO {


	/**
	 * Insert volunteer into database.
	 *
	 * @param volunteer the volunteer
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	public static int insertVolunteerIntoDB(Volunteer volunteer) throws SQLException {
		 	Connection conn = DBConnector.getConnection();
		    PreparedStatement preparedStatement = null;
		    
		    try
		    {	
		    	//1. LocalDate to Date
		    	Date db = Date.valueOf(volunteer.getBirthdate());
		    	
		        //2. Create a String that holds the query with ? as user inputs
		        String sql = "INSERT INTO Volunteer (fName, lName, phone, email, adress, password, birthdate)"
		                + "VALUES (?,?,?,?,?,?,?)";
		                
		        //3. prepare the query
		        preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		               
		        //4. Bind the values to the parameters
		        preparedStatement.setString(1, volunteer.getFirstName());
		        preparedStatement.setString(2, volunteer.getLastName());
		        preparedStatement.setString(3, volunteer.getPhone());
		        preparedStatement.setString(4, volunteer.getEmail());
		        preparedStatement.setString(5, volunteer.getAdress());
		        preparedStatement.setString(6, volunteer.getPass());
		        preparedStatement.setDate(7, db);
		        
		        preparedStatement.executeUpdate();
		        
		        ResultSet keys = preparedStatement.getGeneratedKeys(); 
		        keys.next(); 
		        int key = keys.getInt(1);
		        return key;
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
		    
		 return -1;
	}
	

	/**
	 * Loads volunteers from database.
	 *
	 * @return the observable list
	 * @throws SQLException the SQL exception
	 */
	public static ObservableList<Volunteer> loadVolunteer() throws SQLException {
		 Connection conn = DBConnector.getConnection();
	     Statement statement = null;
	     ResultSet resultSet = null;
	     ObservableList<Volunteer> volunteers = FXCollections.observableArrayList(); 
	     try{
				//create a statement object
				statement = conn.createStatement();

				//create the SQL query
				resultSet = statement.executeQuery("SELECT * FROM Volunteer");
	         while (resultSet.next())
	         {
	             Volunteer volunteer = new Volunteer(resultSet.getString("fName"), 
	            		 				 resultSet.getString("lName"), 
	            		 				 resultSet.getString("phone"), 
	            		 				 resultSet.getString("email"),
	            		 				 resultSet.getString("adress"),
	            		 				 resultSet.getString("password"),
	            		 				 resultSet.getDate("birthdate").toLocalDate());
	             
	             volunteer.setIdV(resultSet.getInt("id"));
	             
	             volunteers.add(volunteer);
	             
	         } 
	         }
	     catch (SQLException e)
	     {
	         System.err.println(e.getMessage());
	     }
		return volunteers;	
	
	}


	/**
	 * Verifies volunteers login from database.
	 *
	 * @param id the id
	 * @param pass the pass
	 * @param volunteer the volunteer
	 * @return the volunteer
	 * @throws SQLException the SQL exception
	 */
	public static Volunteer VerifyLogin(int id, String pass, Volunteer volunteer) throws SQLException {
		
		Connection conn = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM Volunteer WHERE id = ? AND password = ?";
            
            //3.  prepare the statement
            preparedStatement = conn.prepareStatement(sql);
           
            //4.
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, pass);
            
            
            //5. execute the query
            resultSet = preparedStatement.executeQuery();
            
            //6.  extract the password and role from the resultSet
            while (resultSet.next())
            {
            			 volunteer = new Volunteer(resultSet.getString("fName"), 
            									 resultSet.getString("lName"), 
            									 resultSet.getString("phone"), 
            									 resultSet.getString("email"), 
            									 resultSet.getString("adress"), 
            									 resultSet.getString("password"),
            									 resultSet.getDate("birthdate").toLocalDate());
            			 
            	volunteer.setIdV(resultSet.getInt("id"));
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
		return volunteer;
	}
	
	

	/**
	 * Verify registration from database.
	 *
	 * @param vID the v ID
	 * @param eID the e ID
	 * @return the registration
	 * @throws SQLException the SQL exception
	 */
	public static Registration VerifyInsc(int vID, int eID) throws SQLException {
		Connection conn = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        Registration reg = null;
        
        try{
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM Inscricao WHERE idvolunteer = ? AND idevent = ?";
            
            //3.  prepare the statement
            preparedStatement = conn.prepareStatement(sql);
           
            //4.
            preparedStatement.setInt(1, vID);
            preparedStatement.setInt(2, eID);
            
            
            //5. execute the query
            resultSet = preparedStatement.executeQuery();
            
            //6.  extract the password and role from the resultSet
            while (resultSet.next())
            {
            			reg  = new Registration(resultSet.getInt("idvolunteer"), 
            												 resultSet.getInt("idevent"), 
            												 resultSet.getString("titleE"),
            												 resultSet.getDate("date").toLocalDate());
            			 
            	reg.setIdinsc(resultSet.getInt("idinsc"));
            }      
        } 
        catch (Exception e)
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
        return reg;
	}
	

	/**
	 * Changes the information of the volunteer's database.
	 *
	 * @param volunteer the volunteer
	 * @throws SQLException the SQL exception
	 */
	public static void ChangeData(Volunteer volunteer) throws SQLException {

		Connection conn = DBConnector.getConnection();
		   PreparedStatement preparedStatement = null;
		   
		   try
		   {
		   
		       //2. Create a String that holds the query with ? as user inputs
		       String sql = "UPDATE Volunteer SET phone=?, email=?, adress=? WHERE id=?";
		               
		       //3. prepare the query
		       preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		             
		       //4. Bind the values to the parameters
		       preparedStatement.setString(1, volunteer.getPhone());
		       preparedStatement.setString(2, volunteer.getEmail());
		       preparedStatement.setString(3, volunteer.getAdress());
		       preparedStatement.setInt(4, volunteer.getIdV());
		       
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
}
	

