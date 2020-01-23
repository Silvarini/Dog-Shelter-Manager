package pt.iade.dsm.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.DAO.DBConnector;

/**
 * The Class AgeClass.
 */
public class AgeClass {
	
	/** The age ID. */
	private int ageID;
	
	/** The age. */
	private String age;
	
	
	
	

	/**
	 * Load age class.
	 *
	 * @return the observable list
	 */
	public static ObservableList<AgeClass> loadAgeClass() {
		ObservableList<AgeClass> ageClasses =
				FXCollections.observableArrayList();
		
		Connection conn =  DBConnector.getConnection();
		
			String sql = "SELECT * FROM AgeClass";
			try (PreparedStatement stat = conn.prepareStatement(sql)){
				try(ResultSet rs = stat.executeQuery()) {
					while (rs.next()) {
					ageClasses.add(new AgeClass((rs.getInt("ageClassID")),rs.getString("ageClass")));
						 
					
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 

			return ageClasses;
		}

	
/**
 * Verify age.
 *
 * @param ageDog the age dog
 * @param age the age
 * @return the age class
 * @throws SQLException the SQL exception
 */
public static AgeClass VerifyAge(String ageDog, AgeClass age) throws SQLException {
		
		Connection conn = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM AgeClass AND age = ?";
            
            //3.  prepare the statement
            preparedStatement = conn.prepareStatement(sql);
           
            //4.
            preparedStatement.setString(1, ageDog);
            
            
            //5. execute the query
            resultSet = preparedStatement.executeQuery();
            
            //6.  extract the password and role from the resultSet
            while (resultSet.next())
            {
            			 age = new AgeClass(resultSet.getInt("ageID"),resultSet.getString("age"));
            									
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
		return age;
}
	
	
	
	/**
	 * Instantiates a new age class.
	 *
	 * @param ageID the age ID
	 * @param age the age
	 */
	public AgeClass(int ageID, String age) {
		this.ageID = ageID;
		this.age = age;
	}
	
	/**
	 * Gets the age ID.
	 *
	 * @return the age ID
	 */
	public int getAgeID() {
		return ageID;
	}
	
	/**
	 * Sets the age ID.
	 *
	 * @param ageID the new age ID
	 */
	public void setAgeID(int ageID) {
		this.ageID = ageID;
	}
	
	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public String getAge() {
		return age;
	}
	
	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	public void setAge(String age) {
		this.age = age;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString(){
		return this.age;
	}

	
	
	

}
