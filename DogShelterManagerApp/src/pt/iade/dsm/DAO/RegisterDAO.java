package pt.iade.dsm.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.models.Registration;

/**
 * This class connects to the database to insert new registrations on events and gets registrations.
 */
public class RegisterDAO {
	
	/**
	 * Insert registration into database.
	 *
	 * @param reg the reg
	 * @throws SQLException the SQL exception
	 */
	public static void insertRegistrationIntoDB(Registration reg) throws SQLException {
		Connection conn = DBConnector.getConnection();
	    PreparedStatement preparedStatement = null;
	    
	    try
	    {
	       //2. Create a String that holds the query with ? as user inputs
	        String sql = "INSERT INTO Inscricao (idvolunteer, idevent, titleE, date)"
	                + "VALUES (?,?,?,?)";
	                
	        //3. prepare the query
	        preparedStatement = conn.prepareStatement(sql);
	               
	        //4. Bind the values to the parameters
	        preparedStatement.setInt(2, reg.getIdevent());
	        preparedStatement.setString(3, reg.getTitleE());
	        preparedStatement.setDate(4, Date.valueOf(reg.getDate()));
	        preparedStatement.setInt(1, reg.getIdvolunteer());
	        
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
	 * Loads registration from database
	 * returns volunteerID.
	 *
	 * @param vID the v ID
	 * @return the observable list
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static  ObservableList<Registration> loadRegistrations(int vID) throws SQLException, IOException {
		
		Connection conn = DBConnector.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ObservableList<Registration> regs = FXCollections.observableArrayList();
		try {
			 //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM Inscricao WHERE idvolunteer = ?";
            
            //3.  prepare the statement
            preparedStatement = conn.prepareStatement(sql);
           
            //4.
            preparedStatement.setInt(1, vID);
            
            
            //5. execute the query
            resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next())
		{
			Registration reg = new Registration(resultSet.getInt("idvolunteer"), 
									resultSet.getInt("idevent"),
									resultSet.getString("titleE"),
									resultSet.getDate("date").toLocalDate());
			
			reg.setIdinsc(resultSet.getInt("idinsc"));
			
			regs.add(reg);
		}

				
			

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return regs;
		
	}

}