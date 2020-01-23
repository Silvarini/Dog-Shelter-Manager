package pt.iade.dsm.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pt.iade.dsm.DAO.EmployeeDAO;
import pt.iade.dsm.models.Employee;

/**
 * This class is the log in's page controller.
 */
public class LoginInController implements Initializable{

    /** The Username text field. */
    @FXML
    private TextField Username_txfield;

    /** The Password text field. */
    @FXML
	private PasswordField Password_field;

    /** The Back button. */
    @FXML
    private Label BackButton;

    /** The Error message. */
    @FXML
	private Label ErrorMessage;
    
    
    /** The employee. */
    private static Employee employee=null;

    

	/**
	 * Initialize.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ErrorMessage.setText("");
		
	}

    /**
     * This method is a Button that confirms the log in credentials and checks if they are in the database.
     * Changes into the admin's page scene if the credentials match with the admin's credentials in the database.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws SQLException the SQL exception
     */
    @FXML
    void LoginPushed(MouseEvent event) throws IOException, SQLException {
    	
    	if (!Username_txfield.getText().equals("") && !Password_field.getText().equals(""))
    	{
    		employee=EmployeeDAO.VerifyLogin(Username_txfield.getText(), Password_field.getText(), employee);
    		
    		if(employee!=null) {
    			if(employee.getPos_held().getPosition().equals("Administrador"))
    				
    				{
    					SceneChanger.openWindow("views/AdminPage.fxml", new AdminPageController(), event);
    				}
    			
    			else if(employee.getPos_held().getPosition().equals("Funcionário"))
    				{
    					SceneChanger.openWindow("views/EmployeePage.fxml", new EmployeePageController(employee), event);
    				}
    			
    	}
    		else
    			ErrorMessage.setText("The user or the password doesn't match.");
    		
    	}
    	else
    		ErrorMessage.setText("Complete the empty fields.");
    }

	/**
	 * This method returns to the landing page scene.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
    void onBackClicked(MouseEvent event) throws IOException {
    	SceneChanger.openWindow("views/LandingPage.fxml", new LandingPageController(), event);
    }
	
	
	/**
	 * Gets the employee.
	 *
	 * @return the employee
	 */


	/**
	 * Sets the employee.
	 *
	 * @param employee the new employee
	 */
	

	


   


}
