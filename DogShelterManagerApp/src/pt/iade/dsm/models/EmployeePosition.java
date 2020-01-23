package pt.iade.dsm.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.DAO.DBConnector;

/**
 * The Class EmployeePosition.
 */
public class EmployeePosition {
	
	
	
	
	/** The position ID. */
	private int positionID;
	
	/** The position. */
	private String position;
	

	/**
	 * Gets the positions.
	 *
	 * @return the positions
	 */
	public static ObservableList<EmployeePosition> getPositions() {
		ObservableList<EmployeePosition> positions =
				FXCollections.observableArrayList();
		
		Connection conn =  DBConnector.getConnection();
		
		String sql = "SELECT * FROM EmployeePosition";
		try (PreparedStatement stat = conn.prepareStatement(sql)){
			try(ResultSet rs = stat.executeQuery()) {
				while (rs.next()) {
				positions.add(new EmployeePosition((rs.getInt("positionID")),rs.getString("position")));
					 
				
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return positions;
	}
	
	


/**
 * Verify position.
 *
 * @param positionEmployee the position employee
 * @param position the position
 * @return the employee position
 * @throws SQLException the SQL exception
 */
public static EmployeePosition VerifyPosition(String positionEmployee, EmployeePosition position) throws SQLException {
		
		Connection conn = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM EmployeePosition";
            
            //3.  prepare the statement
            preparedStatement = conn.prepareStatement(sql);
           
                        
            
            //5. execute the query
            resultSet = preparedStatement.executeQuery();
            
            //6.  extract the password and role from the resultSet
            while (resultSet.next())
            {
            			 position = new EmployeePosition(resultSet.getInt("positionID"),resultSet.getString("position"));
            									
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
		return position;
	

	}
	
	
	
	
	

	/**
	 * Gets the position ID.
	 *
	 * @return the position ID
	 */
	public int getPositionID() {
		return positionID;
	}




	/**
	 * Sets the position ID.
	 *
	 * @param positionID the new position ID
	 */
	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}




	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}




	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(String position) {
		this.position = position;
	}




	/**
	 * Instantiates a new employee position.
	 *
	 * @param positionID the position ID
	 * @param position the position
	 */
	public EmployeePosition(int positionID, String position) {
		super();
		this.positionID = positionID;
		this.position = position;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString(){
		return this.position;
	}
	
	
}
	
	