package pt.iade.dsm.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.models.Event;

/**
 * The Class EventDAO.
 */
public class EventDAO {


	/**
	 * Insert event into DataBase.
	 *
	 * @param event the event
	 * @throws SQLException the SQL exception
	 */
	public static void insertEventIntoDB(Event event) throws SQLException {
		 Connection conn = DBConnector.getConnection();
		    PreparedStatement preparedStatement = null;
		    
		    try
		    {
		    	//1. LocalDate to SqlDate
		    	Date db = Date.valueOf(event.getEventDate());
		    	
		        //2. Create a String that holds the query with ? as user inputs
		        String sql = "INSERT INTO Events (idE, title, eventDate, description, maxPart, currentPart)"
		                + "VALUES (?,?,?,?,?,?)";
		                
		        //3. prepare the query
		        preparedStatement = conn.prepareStatement(sql);
		               
		        //4. Bind the values to the parameters
		        preparedStatement.setInt(1, event.getIdE());
		        preparedStatement.setString(2, event.getTitle());
		        preparedStatement.setDate(3, db);
		        preparedStatement.setString(4, event.getDescription());
		        preparedStatement.setInt(5, event.getMaxPart());
		        preparedStatement.setInt(6, event.getCurrentPart());
		        
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
	 * Loads event from DataBase.
	 *
	 * @return the observable list
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ObservableList<Event> loadEvents() throws SQLException, IOException {

		Connection conn = DBConnector.getConnection();

		ObservableList<Event> events = FXCollections.observableArrayList();
		try {
			Statement statement = conn.createStatement();
			//create a statement object
			String sql = "SELECT * FROM Events;";
			//create the SQL query
			try ( ResultSet resultSet = statement.executeQuery(sql)) {

				while (resultSet.next())
				{

					Event event = new Event(resultSet.getInt("idE"), 
							resultSet.getString("title"), 
							resultSet.getDate("eventDate").toLocalDate(), 
							resultSet.getString("description"), 
							resultSet.getInt("maxPart"));
					
					event.setCurrentPart(resultSet.getInt("currentPart"));
					event.setId(resultSet.getInt("id"));

					events.add(event);

				}
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return events;
	
}

	/**
	 * Checks the date for events int DataBase.
	 *
	 * @param date the date
	 * @return the observable list
	 * @throws SQLException the SQL exception
	 */
	public static ObservableList<Event> checkDates(Date date) throws SQLException {
		Connection conn = DBConnector.getConnection();
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ObservableList<Event> events = FXCollections.observableArrayList();
		
	        try{
	            
	            //2.  create a query string with ? used instead of the values given by the user
	            String sql = "SELECT * FROM Events WHERE Date(eventDate) = ?";
	            
	            //3.  prepare the statement
	            preparedStatement = conn.prepareStatement(sql);
	           
	            //4.
	            preparedStatement.setDate(1, date);
	            
	            
	            //5. execute the query
	            resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				Event event = new Event(resultSet.getInt("idE"), 
										resultSet.getString("title"),
										resultSet.getDate("eventDate").toLocalDate(),
										resultSet.getString("description"), 
										resultSet.getInt("maxPart"));
				
				event.setId(resultSet.getInt("id"));
				event.setCurrentPart(resultSet.getInt("currentPart"));
				
				events.add(event);
			}
	} 
		
		catch (Exception e)
	{
		System.err.println(e);
	}
		finally
	{
		if (conn != null)
			conn.close();
		if(statement != null)
			statement.close();
		if(resultSet != null)
			resultSet.close();
	}
		return events;
	}
	
	/**
	 * Update event's DataBase.
	 *
	 * @param eventID the event ID
	 * @throws SQLException the SQL exception
	 */
	public static void updatePart(int eventID) throws SQLException {
		Connection conn = DBConnector.getConnection();
	    PreparedStatement preparedStatement = null;
	    
	    try
	    {
	        
	        //2. Create a String that holds the query with ? as user inputs
	        String sql = "UPDATE Events SET currentPart=currentPart+1 WHERE id=?;";
	                
	        //3. prepare the query
	        preparedStatement = conn.prepareStatement(sql);
	        
	        
	        preparedStatement.setInt(1, eventID);
	        
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
