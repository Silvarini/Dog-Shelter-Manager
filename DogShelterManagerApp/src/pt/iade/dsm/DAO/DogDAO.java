package pt.iade.dsm.DAO;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.models.AgeClass;
import pt.iade.dsm.models.Breed;
import pt.iade.dsm.models.CoatLength;
import pt.iade.dsm.models.Dog;
import pt.iade.dsm.models.Gender;
import pt.iade.dsm.models.GoodWith;
import pt.iade.dsm.models.Size;
import pt.iade.dsm.models.StateDog;


/**
 * This class inserts and gets values from dog's table from the database.
 */
public class DogDAO {

	/**
	 * Insert dog into Database.
	 *
	 * @param dog the dog
	 * @param age the age
	 * @param breed the breed
	 * @param coat the coat
	 * @param gender the gender
	 * @param size the size
	 * @param goodWith the good with
	 * @param state the state
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	public static int insertDogIntoDB(Dog dog, AgeClass age, Breed breed, CoatLength coat, Gender gender, Size size, GoodWith goodWith, StateDog state) throws SQLException
	{	
	    Connection conn = DBConnector.getConnection();
	    PreparedStatement preparedStatement = null;
	    
	    try
	    {
	        //2. Create a String that holds the query with ? as user inputs
	        String sql = "INSERT INTO Dog (nameDog, breedDog, ageDog, genderDog, sizeDog,  coatLengthDog, goodWithDog, obs, photoFile, currentState)"
	                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
	                
	        //3. prepare the query
	        preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	               
	        //4. Bind the values to the parameters
	        preparedStatement.setString(1, dog.getName());
	        preparedStatement.setInt(2, breed.getBreedID());
	        preparedStatement.setInt(3, age.getAgeID());
	        preparedStatement.setInt(4, gender.getGenderID());
	        preparedStatement.setInt(5,size.getSizeID());
	        preparedStatement.setInt(6, coat.getCoatID());
	        preparedStatement.setInt(7, goodWith.getGoodWithID());
	        preparedStatement.setString(8, dog.getObs());
	        preparedStatement.setString(9, dog.getPhoto().getName());
	        preparedStatement.setInt(10, state.getStateID());
	        
	        preparedStatement.executeUpdate();
	        
	        ResultSet keys = preparedStatement.getGeneratedKeys();
	        keys.next();
	        int key = keys.getInt(1);
	        return key;
	        
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
	    return -1;
	    
	}
	
	
	/**
	 * Loads values from dog's database.
	 *
	 * @return the observable list
	 * @throws SQLException the SQL exception
	 */
	public static ObservableList<Dog> loadDogs() throws SQLException{

		Connection conn = DBConnector.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		ObservableList<Dog> dogs = FXCollections.observableArrayList();
		
		try{
			//create a statement object
			statement = conn.createStatement();

			//create the SQL query
			resultSet = statement.executeQuery("SELECT dogID, nameDog, obs, photoFile, breedID, breed , ageClassID, ageClass, Gender.genderID, Gender.gender, sizeClassID, sizeClass, cLengthClassID, cLengthClass, goodWithID, goodWith, stateID, state FROM Dog,AgeClass,Breed,CoatLengthClass, Gender,GoodWith, SizeClass,StateType WHERE Dog.ageDog = AgeClass.ageClassID AND Dog.breedDog = Breed.breedID AND Dog.coatLengthDog = CoatLengthClass.cLengthClassID AND Dog.genderDog = Gender.genderID AND Dog.goodWithDog = GoodWith.goodWithID AND Dog.currentState = StateType.stateID AND Dog.sizeDog = SizeClass.sizeClassID");

			while (resultSet.next())
			{
				Dog newDog = new Dog(resultSet.getString("nameDog"),
						new Breed(resultSet.getInt("breedID"), resultSet.getString("breed")),
						new AgeClass(resultSet.getInt("ageClassID"), resultSet.getString("ageClass")),
						new Gender(resultSet.getInt("genderID"), resultSet.getString("gender")),
						new Size(resultSet.getInt("sizeClassID"), resultSet.getString("sizeClass")),
						new CoatLength(resultSet.getInt("cLengthClassID"), resultSet.getString("cLengthClass")),
						new GoodWith(resultSet.getInt("goodWithID"), resultSet.getString("goodWith")),
						resultSet.getString("obs"),
						new StateDog(resultSet.getInt("stateID"), resultSet.getString("state")));
				
				newDog.setId(resultSet.getInt("dogID"));
                newDog.setPhoto(new File(resultSet.getString("photoFile")));
                
				
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
	
	/**
	 * Loads dogs that can be adopted from Database.
	 *
	 * @return the observable list
	 * @throws SQLException the SQL exception
	 */
	public static ObservableList<Dog> loadAdoptableDogs() throws SQLException{

		Connection conn = DBConnector.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		ObservableList<Dog> dogs = FXCollections.observableArrayList();
		
		try{
			//create a statement object
			statement = conn.createStatement();

			//create the SQL query
			resultSet = statement.executeQuery("SELECT dogID,nameDog, obs,photoFile, breedID, breed, ageClassID, ageClass, genderID, gender, sizeClassID, sizeClass,cLengthClassID, cLengthClass, goodWithID, goodWith, stateID, state FROM Dog,AgeClass,Breed,CoatLengthClass, Gender,GoodWith, SizeClass,StateType WHERE Dog.ageDog = AgeClass.ageClassID AND Dog.breedDog = Breed.breedID AND Dog.coatLengthDog = CoatLengthClass.cLengthClassID AND Dog.genderDog = Gender.genderID AND Dog.goodWithDog = GoodWith.goodWithID AND Dog.currentState = StateType.stateID AND Dog.sizeDog = SizeClass.sizeClassID AND (currentState='4' OR currentState='3')");

			while (resultSet.next())
			{
				Dog newDog = new Dog(resultSet.getString("nameDog"),
						new Breed(resultSet.getInt("breedID"), resultSet.getString("breed")),
						new AgeClass(resultSet.getInt("ageClassID"), resultSet.getString("ageClass")),
						new Gender(resultSet.getInt("genderID"), resultSet.getString("gender")),
						new Size(resultSet.getInt("sizeClassID"), resultSet.getString("sizeClass")),
						new CoatLength(resultSet.getInt("cLengthClassID"), resultSet.getString("cLengthClass")),
						new GoodWith(resultSet.getInt("goodWithID"), resultSet.getString("goodWith")),
						resultSet.getString("obs"),
						new StateDog(resultSet.getInt("stateID"), resultSet.getString("state")));
				
				newDog.setId(resultSet.getInt("dogID"));
                newDog.setPhoto(new File(resultSet.getString("photoFile")));
                
				
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
	
	
	
	/**
	 * Change dog state.
	 *
	 * @param dog the dog
	 * @param state the state
	 * @throws SQLException the SQL exception
	 */
	public static void changeDogState (Dog dog, StateDog state) throws SQLException {
		
		Connection conn = DBConnector.getConnection();
	    PreparedStatement preparedStatement = null;
	    
	    try
	    {
	        
	        //2. Create a String that holds the query with ? as user inputs
	        String sql = "UPDATE Dog SET currentState=? WHERE dogID=?;";
	                
	        //3. prepare the query
	        preparedStatement = conn.prepareStatement(sql);
	        
	        
	        preparedStatement.setInt(1, state.getStateID());
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

	/**
	 * Gets the specific dog.
	 *
	 * @param dogID the dog ID
	 * @return the specific dog
	 * @throws SQLException the SQL exception
	 */
	public static Dog getSpecificDog(int dogID) throws SQLException{

		Connection conn = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Dog newDog = null;
        try{
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT nameDog,dogID, breed, breedID, ageClass, ageClassID, gender, genderID,sizeClass, sizeClassID, cLengthClass,  cLengthClassID, goodWith, goodWithID, state, stateID, obs, photoFile FROM Dog, Breed, AgeClass, Gender, SizeClass, CoatLengthClass, GoodWith, StateType WHERE Dog.ageDog = AgeClass.ageClassID AND Dog.breedDog = Breed.breedID AND Dog.coatLengthDog = CoatLengthClass.cLengthClassID AND Dog.genderDog = Gender.genderID AND Dog.goodWithDog = GoodWith.goodWithID AND Dog.currentState = StateType.stateID AND Dog.sizeDog = SizeClass.sizeClassID AND dogID = ?";
            
            //3.  prepare the statement
            preparedStatement = conn.prepareStatement(sql);
           
            //4.
            preparedStatement.setInt(1, dogID);
            
            
            //5. execute the query
            resultSet = preparedStatement.executeQuery();
            
            //6.  extract the password and role from the resultSet
            while (resultSet.next())
            {
            	newDog = new Dog(resultSet.getString("nameDog"),
            			new Breed(resultSet.getInt("breedID"), resultSet.getString("breed")),
						new AgeClass(resultSet.getInt("ageClassID"), resultSet.getString("ageClass")),
						new Gender(resultSet.getInt("genderID"), resultSet.getString("gender")),
						new Size(resultSet.getInt("sizeClassID"), resultSet.getString("sizeClass")),
						new CoatLength(resultSet.getInt("cLengthClassID"), resultSet.getString("cLengthClass")),
						new GoodWith(resultSet.getInt("goodWithID"), resultSet.getString("goodWith")),
						resultSet.getString("obs"),
            			new StateDog(resultSet.getInt("stateID"), resultSet.getString("state")));;
            			
            	newDog.setId(resultSet.getInt("dogID"));
                newDog.setPhoto(new File(resultSet.getString("photoFile")));
               
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
	
	/**
	 * Updates dog's Database.
	 *
	 * @param dog the dog
	 * @param size the size
	 * @param coat the coat
	 * @param age the age
	 * @param state the state
	 * @throws SQLException the SQL exception
	 */
	public static void updateDog(Dog dog, Size size, CoatLength coat, AgeClass age, StateDog state) throws SQLException {
		Connection conn = DBConnector.getConnection();
	    PreparedStatement preparedStatement = null;
	 
	    try { 
	    //2. Create a String that holds the query with ? as user inputs
        String sql = "UPDATE Dog SET sizeDog=?, coatLengthDog=?, ageDog=?, currentState=?, obs=? WHERE dogID=?;";
              
        //3. prepare the query
        preparedStatement = conn.prepareStatement(sql);
        
        preparedStatement.setInt(1, size.getSizeID());
        preparedStatement.setInt(2, coat.getCoatID());
        preparedStatement.setInt(3, age.getAgeID());
        preparedStatement.setInt(4, state.getStateID());
        preparedStatement.setString(5, dog.getObs());
        preparedStatement.setInt(6, dog.getId());
        
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
