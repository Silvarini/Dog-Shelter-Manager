package pt.iade.dsm.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.models.State;

// TODO: Auto-generated Javadoc
/**
 * The Class HistoricDAO.
 */
public class HistoricDAO {

	/**
	 * Insert state DB.
	 *
	 * @param state the state
	 * @throws SQLException the SQL exception
	 */
	public static void insertStateDB(State state) throws SQLException {
		Connection conn = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        
        try
        {   
            //2. Create a String that holds the query with ? as user inputs
            String sql = "INSERT INTO Historic (stateType, dogID, employeeID)"
                    + "VALUES (?,?,?)";
                    
            //3. prepare the query
            preparedStatement = conn.prepareStatement(sql);
            
            //5. Bind the values to the parameters
            preparedStatement.setString(1, state.getState());
            preparedStatement.setInt(2, state.getDogID());
            preparedStatement.setInt(3, state.getEmployeeID());
            
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
	 * Load state.
	 *
	 * @return the observable list
	 * @throws SQLException the SQL exception
	 */
	public static ObservableList<State> loadState() throws SQLException {
		 Connection conn = DBConnector.getConnection();
	     Statement statement = null;
	     ResultSet resultSet = null;
	     ObservableList<State> states = FXCollections.observableArrayList(); 
	     try{
				//create a statement object
				statement = conn.createStatement();

				//create the SQL query
				resultSet = statement.executeQuery("SELECT * FROM Historic");
	         while (resultSet.next())
	         {
	             State state = new State(resultSet.getString("stateType"), 
	            		 				 resultSet.getInt("dogID"), 
	            		 				 resultSet.getInt("employeeID")); 
	             
	             state.setInitialDate(resultSet.getDate("initialDate").toLocalDate());;
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