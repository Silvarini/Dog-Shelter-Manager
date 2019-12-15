package pt.iade.dsm.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pt.iade.dsm.DAO.AdoptionRequestDAO;
import pt.iade.dsm.DAO.GuestDAO;
import pt.iade.dsm.models.Adoption;
import pt.iade.dsm.models.Guest;

/**
 * This class is the controller for the adoption pop up on the guest's page 
 * where the information and the behavior of the interface get manipulated.
 */
public class AdoptionPopUpController implements Initializable {

	/** The text field first name. */
	@FXML
    private TextField fName;

    /** The text field phone. */
    @FXML
    private TextField phone;

    /** The text field email. */
    @FXML
    private TextField email;

    /** The text field last name. */
    @FXML
    private TextField lName;

    /** The text area conditions. */
    @FXML
    private TextArea conditions;

    /** The text area adress. */
    @FXML
    private TextArea adress;

    /** The submit message. */
    @FXML
    private Label submitMessage;

	    /**
    	 * On submit pushed.
    	 * Sets a new guest's values into the database.
    	 * Sets a new adoption's values into the database
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void onSubmitPushed(ActionEvent event)  {
	    	try {
	    	Guest guest = new Guest(fName.getText(), lName.getText(), phone.getText(), email.getText(), adress.getText(), conditions.getText());
	    	Adoption adoption = new Adoption(phone.getText(), DogsListController.getDog().getId(), "on hold");
	    	GuestDAO.insertGuestDB(guest);
	    	AdoptionRequestDAO.insertAdoptionRequestDB(adoption);
	    	
	    	submitMessage.setText("Adoption request saved, please wait patiently for approval");	    	
	    }
	    	catch (Exception e) {
	    		submitMessage.setText(e.getMessage());
	    	}

	    }

		/**
		 * Initialize.
		 *
		 * @param location the location
		 * @param resources the resources
		 */
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			submitMessage.setText("");
		}
	
	
}
