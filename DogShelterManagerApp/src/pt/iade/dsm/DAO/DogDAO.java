package pt.iade.dsm.DAO;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.models.Dog;


public class DogDAO {

	public static void insertDogIntoDB(Dog dog) throws SQLException
	{	
	    Connection conn = DBConnector.getConnection();
	    PreparedStatement preparedStatement = null;
	    
	    try
	    {
	        //2. Create a String that holds the query with ? as user inputs
	        String sql = "INSERT INTO Dog (nameDog, breedDog, ageDog, genderDog, sizeDog,  coatLengthDog, goodWithDog, obs, photoFile, currentState)"
	                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
	                
	        //3. prepare the query
	        preparedStatement = conn.prepareStatement(sql);
	               
	        //4. Bind the values to the parameters
	        preparedStatement.setString(1, dog.getName());
	        preparedStatement.setString(2, dog.getBreed());
	        preparedStatement.setString(3, dog.getAge());
	        preparedStatement.setString(4, dog.getGender());
	        preparedStatement.setString(5, dog.getSize());
	        preparedStatement.setString(6, dog.getCoat());
	        preparedStatement.setString(7, dog.getGoodw());
	        preparedStatement.setString(8, dog.getObs());
	        preparedStatement.setString(9, dog.getPhoto().getName());
	        preparedStatement.setString(10, dog.getState());
	        
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
	
	
	public static ObservableList<Dog> loadDogs() throws SQLException{

		Connection conn = DBConnector.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		ObservableList<Dog> dogs = FXCollections.observableArrayList();
		
		try{
			//create a statement object
			statement = conn.createStatement();

			//create the SQL query
			resultSet = statement.executeQuery("SELECT * FROM Dog");

			while (resultSet.next())
			{
				Dog newDog = new Dog(resultSet.getString("nameDog"),
						resultSet.getString("breedDog"),
						resultSet.getString("ageDog"),
						resultSet.getString("genderDog"),
						resultSet.getString("sizeDog"),
						resultSet.getString("coatLengthDog"),
						resultSet.getString("goodWithDog"),
						resultSet.getString("obs"));
				
				newDog.setId(resultSet.getInt("dogID"));
                newDog.setPhoto(new File(resultSet.getString("photoFile")));
                newDog.setState(resultSet.getString("currentState"));
				
				dogs.add(newDog);
				
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
		return dogs;
}
	public static void changeDogState (Dog dog) throws SQLException {
		
		Connection conn = DBConnector.getConnection();
	    PreparedStatement preparedStatement = null;
	    
	    try
	    {
	        
	        //2. Create a String that holds the query with ? as user inputs
	        String sql = "UPDATE Dog SET currentState=? WHERE dogID=?;";
	                
	        //3. prepare the query
	        preparedStatement = conn.prepareStatement(sql);
	        
	        
	        preparedStatement.setString(1, dog.getState());
	        preparedStatement.setInt(2, dog.getId());
	        
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

	public static Dog getSpecificDog(Dog dog) throws SQLException{

		Connection conn = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Dog newDog = null;
        try{
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM Dog WHERE dogID = ?";
            
            //3.  prepare the statement
            preparedStatement = conn.prepareStatement(sql);
           
            //4.
            preparedStatement.setInt(1, dog.getId());
            
            
            //5. execute the query
            resultSet = preparedStatement.executeQuery();
            
            //6.  extract the password and role from the resultSet
            while (resultSet.next())
            {
            	newDog = new Dog(resultSet.getString("nameDog"),
						resultSet.getString("breedDog"),
						resultSet.getString("ageDog"),
						resultSet.getString("genderDog"),
						resultSet.getString("sizeDog"),
						resultSet.getString("coatLengthDog"),
						resultSet.getString("goodWithDog"),
						resultSet.getString("obs"));
				
                newDog.setPhoto(new File(resultSet.getString("photoFile")));
                newDog.setState(resultSet.getString("currentState"));	 
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
		return newDog;
}
}
