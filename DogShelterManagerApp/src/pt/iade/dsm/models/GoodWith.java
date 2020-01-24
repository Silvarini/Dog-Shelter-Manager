package pt.iade.dsm.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.DAO.DBConnector;

/**
 * This model represents the dog's good with class, where you have the constructor and DAO.
 */
public class GoodWith {
	
	/** The good with ID. */
	private int goodWithID;
	
	/** The good with. */
	private String goodWith;
	
	
	
	

	/**
	 * Load good with.
	 *
	 * @return the observable list
	 */
	public static ObservableList<GoodWith> loadGoodWith() {
		ObservableList<GoodWith> goodWiths =
				FXCollections.observableArrayList();
		
		Connection conn =  DBConnector.getConnection();
		
			String sql = "SELECT * FROM GoodWith";
			try (PreparedStatement stat = conn.prepareStatement(sql)){
				try(ResultSet rs = stat.executeQuery()) {
					while (rs.next()) {
					goodWiths.add(new GoodWith((rs.getInt("goodWithID")),rs.getString("goodWith")));
						 
					
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 

			return goodWiths;
		}

	
/**
 * Verify good with.
 *
 * @param goodWithDog the good with dog
 * @param goodWith the good with
 * @return the good with
 * @throws SQLException the SQL exception
 */
public static GoodWith VerifyGoodWith(String goodWithDog, GoodWith goodWith) throws SQLException {
		
		Connection conn = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM GoodWith AND goodWith = ?";
            
            //3.  prepare the statement
            preparedStatement = conn.prepareStatement(sql);
           
            //4.
            preparedStatement.setString(1, goodWithDog);
            
            
            //5. execute the query
            resultSet = preparedStatement.executeQuery();
            
            //6.  extract the password and role from the resultSet
            while (resultSet.next())
            {
            			 goodWith = new GoodWith(resultSet.getInt("goodWithID"),resultSet.getString("goodWith"));
            									
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
		return goodWith;
}





/**
 * Instantiates a new good with.
 *
 * @param goodWithID the good with ID
 * @param goodWith the good with
 */
public GoodWith(int goodWithID, String goodWith) {
	this.goodWithID = goodWithID;
	this.goodWith = goodWith;
}


/**
 * Gets the good with ID.
 *
 * @return the good with ID
 */
public int getGoodWithID() {
	return goodWithID;
}


/**
 * Sets the good with ID.
 *
 * @param goodWithID the new good with ID
 */
public void setGoodWithID(int goodWithID) {
	this.goodWithID = goodWithID;
}


/**
 * Gets the good with.
 *
 * @return the good with
 */
public String getGoodWith() {
	return goodWith;
}


/**
 * Sets the good with.
 *
 * @param goodWith the new good with
 */
public void setGoodWith(String goodWith) {
	this.goodWith = goodWith;
}

/**
 * To string.
 *
 * @return the string
 */
public String toString(){
	return this.goodWith;
}




	

	
	
	

}
