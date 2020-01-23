package pt.iade.dsm.DAO;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.iade.dsm.models.Employee;
import pt.iade.dsm.models.EmployeePosition;
import pt.iade.dsm.models.Gender;

/**
 * This class inserts and gets values from employee's table in the database.
 */
public class EmployeeDAO {




	/**
	 * Insert employee into DB.
	 *
	 * @param employee the employee
	 * @param gender the gender
	 * @param position the position
	 * @throws SQLException the SQL exception
	 */
	public static void insertEmployeeIntoDB(Employee employee, Gender gender, EmployeePosition position) throws SQLException
	{
		Connection conn = DBConnector.getConnection();
		PreparedStatement preparedStatement = null;

		try
		{
			//2. Create a String that holds the query with ? as user inputs
			String sql = "INSERT INTO Employee (nameEmployee, username, password, genderEmployee, birthdate,  positionHeld, PhotoFile)" 
						   + "VALUES (?,?,?,?,?,?,?)";

			//3. prepare the query
			preparedStatement = conn.prepareStatement(sql);

			//4. Convert the birthday into a SQL date
			Date db = Date.valueOf(employee.getBirthdate());

			//5. Bind the values to the parameters
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getUsername());
			preparedStatement.setString(3, employee.getPassword());
			preparedStatement.setInt(4, gender.getGenderID());
			preparedStatement.setDate(5, db);
			preparedStatement.setInt(6, position.getPositionID());
			preparedStatement.setString(7, employee.getPhoto().getName());

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
	 * Load employees.
	 *
	 * @return the observable list
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ObservableList<Employee> loadEmployees() throws SQLException, IOException {

		Connection conn = DBConnector.getConnection();

		ObservableList<Employee> employees = FXCollections.observableArrayList();
		try {
			Statement statement = conn.createStatement();
			//create a statement object
			String sql = "SELECT nameEmployee, employeeID, username,password, genderEmployee, Gender.gender, Gender.genderID, birthdate, positionHeld, EmployeePosition.position, EmployeePosition.positionID, PhotoFile FROM Employee, Gender, EmployeePosition WHERE genderEmployee = Gender.genderID AND positionHeld = EmployeePosition.positionID;";
			//create the SQL query
			try ( ResultSet resultSet = statement.executeQuery(sql)) {

				while (resultSet.next())
				{

					Employee employee = new Employee(resultSet.getString("nameEmployee"), 
							resultSet.getString("username"), 
							resultSet.getString("password"), 
							new Gender(resultSet.getInt("Gender.genderID"),resultSet.getString("Gender.gender")), 
							resultSet.getDate("birthdate").toLocalDate(), 
							new EmployeePosition(resultSet.getInt("EmployeePosition.positionID"), resultSet.getString("EmployeePosition.position")));
					employee.setEmployeeID(resultSet.getInt("employeeID"));
					employee.setPhoto(new File(resultSet.getString("PhotoFile")));

					employees.add(employee);

				}
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return employees;
	}


	
	/**
	 * Verify login.
	 *
	 * @param username the username
	 * @param password the password
	 * @param employee the employee
	 * @return the employee
	 * @throws SQLException the SQL exception
	 */
	public static Employee VerifyLogin(String username, String password, Employee employee) throws SQLException {
		
		Connection conn = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT nameEmployee, username, password, genderEmployee, Gender.gender, Gender.genderID, birthdate, positionHeld, EmployeePosition.position, EmployeePosition.positionID, employeeID, PhotoFile FROM Employee, Gender, EmployeePosition WHERE positionHeld = EmployeePosition.positionID AND genderEmployee = Gender.genderID AND username = ? AND password = ?;";
            
            //3.  prepare the statement
            preparedStatement = conn.prepareStatement(sql);
           
            //4.
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            
            //5. execute the query
            resultSet = preparedStatement.executeQuery();
            
            //6.  extract the password and role from the resultSet
            while (resultSet.next())
            {
            			 employee = new Employee(resultSet.getString("nameEmployee"), 
            									 resultSet.getString("username"), 
            									 resultSet.getString("password"), 
            						new Gender(resultSet.getInt("Gender.genderID"),resultSet.getString("Gender.gender")), 
            									 resultSet.getDate("birthdate").toLocalDate(), 
            						new EmployeePosition(resultSet.getInt("EmployeePosition.positionID"), resultSet.getString("EmployeePosition.position")));
            			 
            	employee.setEmployeeID(resultSet.getInt("employeeID"));
            	employee.setPhoto(new File(resultSet.getString("PhotoFile")));
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
		return employee;
	

	}
	
}
