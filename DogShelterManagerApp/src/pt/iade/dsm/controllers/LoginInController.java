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

public class LoginInController implements Initializable{

    @FXML
    private TextField Username_txfield;

    @FXML
	private PasswordField Password_field;

    @FXML
    private Label BackButton;

    @FXML
	private Label ErrorMessage;
    
    
    private static Employee employee=null;

    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ErrorMessage.setText("");
		
	}

    @FXML
    void LoginPushed(MouseEvent event) throws IOException, SQLException {
    	
    	if (!Username_txfield.getText().equals("") && !Password_field.getText().equals(""))
    	{
    		employee=EmployeeDAO.VerifyLogin(Username_txfield.getText(), Password_field.getText(), employee);
    		
    		if(employee!=null && employee.getPassword().equals(Password_field.getText())) {
    			if(employee.getPos_held().equals("Administrador"))
    				{
    					SceneChanger.openWindow("views/AdminPage.fxml", new AdminPageController(), event);
    				}
    			else if(employee.getPos_held().equals("Funcionário"))
    				{
    					SceneChanger.openWindow("views/EmployeePage.fxml", new EmployeePageController(), event);
    				}
    			
    	}
    		else
    			ErrorMessage.setText("The user or the password doesn't match.");
    		
    	}
    	else
    		ErrorMessage.setText("Complete the empty fields.");
    }

	@FXML
    void onBackClicked(MouseEvent event) throws IOException {
    	SceneChanger.openWindow("views/LandingPage.fxml", new LandingPageController(), event);
    }
	
	
	public static Employee getEmployee() {
		return employee;
	}

	public static void setEmployee(Employee employee) {
		LoginInController.employee = employee;
	}

	


   


}
