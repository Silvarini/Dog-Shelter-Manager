package pt.iade.dsm.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.models.Historic;
import pt.iade.dsm.models.StateDog;

/**
 * This class connects to the database to get and set values from the historic's table.
 */
public class HistoricDAO {

	/**
	 * Insert state into historic's database.
	 *
	 * @param historic the state
	 * @param state the state
	 * @throws SQLException the SQL exception
	 */
	public static void insertStateDB(Historic historic, StateDog state) throws SQLException {
		Connection conn = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        
        try
        {   
            //2. Create a String that holds the query with ? as user inputs
            String sql = "INSERT INTO Historic (stateType, dogID, employeeID)"
                    + "VALUES (?,?,?);";
                    
            //3. prepare the query
            preparedStatement = conn.prepareStatement(sql);
            
            //5. Bind the values to the parameters
            preparedStatement.setInt(1, state.getStateID());
            preparedStatement.setInt(2, historic.getDogID());
            preparedStatement.setInt(3, historic.getEmployeeID());
            
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
	 * Load state form database.
	 *
	 * @return the observable list
	 * @throws SQLException the SQL exception
	 */
	public static ObservableList<Historic> loadState() throws SQLException {
		 Connection conn = DBConnector.getConnection();
	     Statement statement = null;
	     ResultSet resultSet = null;
	     ObservableList<Historic> states = FXCollections.observableArrayList(); 
	     try{
				//create a statement object
				statement = conn.createStatement();

				//create the SQL query
				resultSet = statement.executeQuery("SELECT idHistoric, stateID, state, dogID, employeeID, initialDate FROM Historic, StateType WHERE stateType = stateID");
	         while (resultSet.next())
	         {
	             Historic state = new Historic(new StateDog(resultSet.getInt("stateID"),resultSet.getString("state")), 
	            		 				 resultSet.getInt("dogID"), 
	            		 				 resultSet.getInt("employeeID")); 
	             
	             state.setInitialDate(resultSet.getDate("initialDate").toLocalDate());
	             state.setId(resultSet.getInt("idHistoric"));
	             states.add(state);
	             
	         } 
	         }
	     catch (SQLException e)
	     {
	         System.err.println(e.getMessage());
	     }
		return states;
	
}
}