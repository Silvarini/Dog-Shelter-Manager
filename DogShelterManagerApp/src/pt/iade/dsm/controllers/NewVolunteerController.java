package pt.iade.dsm.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import pt.iade.dsm.DAO.VolunteerDAO;
import pt.iade.dsm.models.Volunteer;


/**
 * This class is the new volunteer's page controller.
 */
public class NewVolunteerController implements Initializable {

	
	/*First Name text field*/
	@FXML
    private TextField fName;
	
	/*Last Name text field*/   
	@FXML
    private TextField lName;
    
	/*Email text field*/
	@FXML
    private TextField email;

	/*Phone text field*/
    @FXML
    private TextField phone;

    /*Address text field*/
    @FXML
    private TextField address;

    /*Password field*/
    @FXML
    private PasswordField password;
    
    /*Birthdate picker*/
    @FXML
    private DatePicker birthdate;

    /*Message*/
    @FXML
    private Label eMsg;

    /**
     * On register.
     *
     * @param event the event
     */
    /*Inserts the new volunteer's information*/
    @FXML
    void onRegister(ActionEvent event) {
    	
    	Volunteer volunteer = new Volunteer(fName.getText(), lName.getText(), phone.getText(), email.getText(), address.getText(), password.getText(), birthdate.getValue());
    	try {
			
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("IMPORTANT");
	    	alert.setHeaderText("Volunteer ID = "
	    						+VolunteerDAO.insertVolunteerIntoDB(volunteer)+
	    						".");
	    	alert.setContentText(" Use it to access the volunteer area.");

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if(result.get() == ButtonType.OK) {
	    	}
	    	
		} catch (SQLException e) {
			eMsg.setText(e.getMessage());
		}
    	

    }

	/**
	 * Initialize.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		eMsg.setText("");
	}

	
}
