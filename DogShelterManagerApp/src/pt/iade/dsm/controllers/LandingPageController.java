package pt.iade.dsm.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
//import pt.iade.dsm.comunication.PhoneMessage;

/**
 * This is the landing page's class.
 */
public class LandingPageController {	
    	
    
	
	/**
	 * This method  opens dogs list's page.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	@FXML
    void onAnimalsPushed(ActionEvent event) throws IOException, SQLException {	
		SceneChanger.openWindow("views/DogsPage.fxml", new DogsListController(), event);
    }


    /**
     *This method opens log in page.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void onStaffClicked(MouseEvent event) throws IOException {
    	SceneChanger.openWindow("views/LoginEmployee.fxml", new LoginInController(), event);
    }
    

    /**
     * This method will open volunteers page.
     * This is still in development...
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void onVolunteersPushed(ActionEvent event) throws IOException {
    	SceneChanger.openWindow("views/LoginVolunteer.fxml", new LoginInVolunteer(), event);
    }

	
    

}
