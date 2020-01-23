package pt.iade.dsm.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.DAO.DBConnector;

/**
 * The Class Breed.
 */
public class Breed {
	
	/** The breed ID. */
	private int breedID;
	
	/** The breed. */
	private String breed;
	
	
	
	

	/**
	 * Load breeds.
	 *
	 * @return the observable list
	 */
	public static ObservableList<Breed> loadBreeds() {
		ObservableList<Breed> breeds =
				FXCollections.observableArrayList();
		
		Connection conn =  DBConnector.getConnection();
		
			String sql = "SELECT * FROM Breed";
			try (PreparedStatement stat = conn.prepareStatement(sql)){
				try(ResultSet rs = stat.executeQuery()) {
					while (rs.next()) {
					breeds.add(new Breed((rs.getInt("breedID")),rs.getString("breed")));
						 
					
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 

			return breeds;
		}

	
/**
 * Verify breed.
 *
 * @param breedDog the breed dog
 * @param breed the breed
 * @return the breed
 * @throws SQLException the SQL exception
 */
public static Breed VerifyBreed(String breedDog, Breed breed) throws SQLException {
		
		Connection conn = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM Breed AND breed = ?";
            
            //3.  prepare the statement
            preparedStatement = conn.prepareStatement(sql);
           
            //4.
            preparedStatement.setString(1, breedDog);
            
            
            //5. execute the query
            resultSet = preparedStatement.executeQuery();
            
            //6.  extract the password and role from the resultSet
            while (resultSet.next())
            {
            			 breed = new Breed(resultSet.getInt("breedID"),resultSet.getString("breed"));
            									
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
		return breed;
}





/**
 * Instantiates a new breed.
 *
 * @param breedID the breed ID
 * @param breed the breed
 */
public Breed(int breedID, String breed) {
	this.breedID = breedID;
	this.breed = breed;
}


/**
 * Gets the breed ID.
 *
 * @return the breed ID
 */
public int getBreedID() {
	return breedID;
}


/**
 * Sets the breed ID.
 *
 * @param breedID the new breed ID
 */
public void setBreedID(int breedID) {
	this.breedID = breedID;
}


/**
 * Gets the breed.
 *
 * @return the breed
 */
public String getBreed() {
	return breed;
}


/**
 * Sets the breed.
 *
 * @param breed the new breed
 */
public void setBreed(String breed) {
	this.breed = breed;
}

/**
 * To string.
 *
 * @return the string
 */
public String toString(){
	return this.breed;
}

	
	
	

	

}
