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

public class EmployeeDAO {




	public static void insertEmployeeIntoDB(Employee employee) throws SQLException
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
			preparedStatement.setString(4, employee.getGender());
			preparedStatement.setDate(5, db);
			preparedStatement.setString(6, employee.getPos_held());
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


	public static ObservableList<Employee> loadEmployees() throws SQLException, IOException {

		Connection conn = DBConnector.getConnection();

		ObservableList<Employee> employees = FXCollections.observableArrayList();
		try {
			Statement statement = conn.createStatement();
			//create a statement object
			String sql = "SELECT * FROM Employee;";
			//create the SQL query
			try ( ResultSet resultSet = statement.executeQuery(sql)) {

				while (resultSet.next())
				{

					Employee employee = new Employee(resultSet.getString("nameEmployee"), 
							resultSet.getString("username"), 
							resultSet.getString("password"), 
							resultSet.getString("genderEmployee"), 
							resultSet.getDate("birthdate").toLocalDate(), 
							resultSet.getString("positionHeld"));
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


	
	public static Employee VerifyLogin(String username, String password, Employee employee) throws SQLException {
		
		Connection conn = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM Employee WHERE username = ?";
            
            //3.  prepare the statement
            preparedStatement = conn.prepareStatement(sql);
           
            //4.
            preparedStatement.setString(1, username);
            
            
            //5. execute the query
            resultSet = preparedStatement.executeQuery();
            
            //6.  extract the password and role from the resultSet
            while (resultSet.next())
            {
            			 employee = new Employee(resultSet.getString("nameEmployee"), 
            									 resultSet.getString("username"), 
            									 resultSet.getString("password"), 
            									 resultSet.getString("genderEmployee"), 
            									 resultSet.getDate("birthdate").toLocalDate(), 
            									 resultSet.getString("positionHeld"));
            			 
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
