package pt.iade.dsm.models;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.DAO.DBConnector;

/**
 * This model represents the size class, where you have the constructor and DAO.
 */
public class Size {
	
	/** The size ID. */
	private int sizeID;
	
	/** The size. */
	private String size;
	
	
	


	/**
	 * Load sizes.
	 *
	 * @return the observable list
	 */
	public static ObservableList<Size> loadSizes() {
		ObservableList<Size> sizes =
				FXCollections.observableArrayList();
		
		Connection conn =  DBConnector.getConnection();
		
			String sql = "SELECT * FROM SizeClass";
			try (PreparedStatement stat = conn.prepareStatement(sql)){
				try(ResultSet rs = stat.executeQuery()) {
					while (rs.next()) {
					sizes.add(new Size((rs.getInt("sizeClassID")),rs.getString("sizeClass")));
						 
					
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 

			return sizes;
		}

	
/**
 * Verify size.
 *
 * @param sizeDog the size dog
 * @param size the size
 * @return the size
 * @throws SQLException the SQL exception
 */
public static Size VerifySize(String sizeDog, Size size) throws SQLException {
		
		Connection conn = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM SizeClass AND sizeClass = ?";
            
            //3.  prepare the statement
            preparedStatement = conn.prepareStatement(sql);
           
            //4.
            preparedStatement.setString(1, sizeDog);
            
            
            //5. execute the query
            resultSet = preparedStatement.executeQuery();
            
            //6.  extract the password and role from the resultSet
            while (resultSet.next())
            {
            			 size = new Size(resultSet.getInt("sizeClassID"),resultSet.getString("sizeClass"));
            									
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
		return size;
}





/**
 * Instantiates a new size.
 *
 * @param sizeID the size ID
 * @param size the size
 */
public Size(int sizeID, String size) {
	this.sizeID = sizeID;
	this.size = size;
}


/**
 * Gets the size ID.
 *
 * @return the size ID
 */
public int getSizeID() {
	return sizeID;
}


/**
 * Sets the size ID.
 *
 * @param sizeID the new size ID
 */
public void setSizeID(int sizeID) {
	this.sizeID = sizeID;
}


/**
 * Gets the size.
 *
 * @return the size
 */
public String getSize() {
	return size;
}


/**
 * Sets the size.
 *
 * @param size the new size
 */
public void setSize(String size) {
	this.size = size;
}

/**
 * To string.
 *
 * @return the string
 */
public String toString(){
	return this.size;
}









	

	
	
	

}
