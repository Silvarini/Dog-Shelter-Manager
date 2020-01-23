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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import pt.iade.dsm.DAO.VolunteerDAO;
import pt.iade.dsm.models.Volunteer;

/**
 * This class is the controller for the volunteer's profile, 
 * where the information and the behavior of the interface get manipulated.
 * This is the controller for the page where the employee edits his values.
 */
public class EditVolunteerControlller implements Initializable{
	
		/** The phone. */
		/*Phone's textfield*/
	    @FXML
	    private TextField phone;

	    /** The email. */
    	/*Email's textfield*/
	    @FXML
	    private TextField email;

	    /** The adress. */
    	/*Adress's textfield*/
	    @FXML
	    private TextField adress;
	    
	    /** The e msg. */
    	/*Message*/
	    @FXML
	    private Label eMsg;
	    
	    /** The volunteer. */
    	/*Gets the information from the controller of LoginVolunteer*/
	    private Volunteer volunteer = LoginInVolunteer.volunteer;

	    
	    /**
    	 * On save pushed.
    	 *
    	 * @param event the event
    	 */
    	/*Insert's the new volunteer's information*/
	    @FXML
	    void onSavePushed(ActionEvent event)  {
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Confirmation");
	    	alert.setHeaderText("Confirm");
	    	alert.setContentText("Do you want to submit the information?");

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	  
	    	volunteer.setPhone(phone.getText());
	    	volunteer.setEmail(email.getText());
	    	volunteer.setAdress(adress.getText());
	    	
	    	try {
				VolunteerDAO.ChangeData(volunteer);
				eMsg.setText("Change successful.");
			} catch (SQLException e) {
				eMsg.setText(e.getMessage());
			}
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
			
			phone.setText(volunteer.getPhone());
			email.setText(volunteer.getEmail());
			adress.setText(volunteer.getAdress());
			
		}


}
