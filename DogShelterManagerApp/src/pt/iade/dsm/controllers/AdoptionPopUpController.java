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

public class AdoptionPopUpController implements Initializable {

	@FXML
    private TextField fName;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextField lName;

    @FXML
    private TextArea conditions;

    @FXML
    private TextArea adress;

    @FXML
    private Label submitMessage;

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

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			submitMessage.setText("");
		}
	
	
}
