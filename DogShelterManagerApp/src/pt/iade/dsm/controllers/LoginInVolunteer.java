package pt.iade.dsm.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pt.iade.dsm.DAO.VolunteerDAO;
import pt.iade.dsm.models.Volunteer;


/**
 * This class is the controller of the logIn of the volunteer.
 * 
 */
public class LoginInVolunteer implements Initializable{

	/** The id V. */
	/*volunteer id's text field*/
	@FXML
    private TextField idV;

	/** The pw. */
	/*password*/
    @FXML
    private PasswordField pw;

    /** The e msg. */
    /*message*/
    @FXML
    private Label eMsg;
    
    /** The volunteer. */
    /*Volunteer object*/
    public static Volunteer volunteer = null;

    /**
     * Gets the volunteer.
     *
     * @return the volunteer
     */
    /*Get volunteer*/
    public static Volunteer getVolunteer() {
		return volunteer;
	}

    /**
     * Sets the volunteer.
     *
     * @param volunteer the new volunteer
     */
    /*Sets volunteer*/
	public static void setVolunteer(Volunteer volunteer) {
		LoginInVolunteer.volunteer = volunteer;
	}

	/**
	 * On here.
	 *
	 * @param event the event
	 */
	/*Opens page where a new volunteer can register*/
	@FXML
    void onHere(MouseEvent event) {
    	PopUpDisplayer.showPopupWindow("views/CreateVolunteer.fxml", new NewVolunteerController());
    }

	/**
	 * On login.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	/*Volunteer's login.
	 * Verifies if the volunteer exists
	 * Opens Volunteer's page*/
    @FXML
    void onLogin(ActionEvent event) throws IOException {
    	try {
			volunteer = VolunteerDAO.VerifyLogin(Integer.valueOf(idV.getText()), pw.getText(), volunteer);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
    		if(volunteer!=null)
    			SceneChanger.openWindow("views/VolunteerProfile.fxml", new VolunteerPageController(), event);
    		else
    			eMsg.setText("The ID or the password doesn't match.");
    }
    
    /**
     * On back clicked.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void onBackClicked(MouseEvent event) throws IOException {
    	SceneChanger.openWindow("views/LandingPage.fxml", new LandingPageController(), event);
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
