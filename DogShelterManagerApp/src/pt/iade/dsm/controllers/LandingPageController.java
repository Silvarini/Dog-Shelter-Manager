package pt.iade.dsm.controllers;

import java.io.IOException;
import java.sql.SQLException;

import com.nexmo.client.NexmoClientException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
//import pt.iade.dsm.comunication.PhoneMessage;

public class LandingPageController {	
    	
    
	
	@FXML
    void onAnimalsPushed(ActionEvent event) throws IOException, SQLException {	
		SceneChanger.openWindow("views/DogsPage.fxml", new DogsListController(), event);
    }

    @FXML
    void onDonationsPushed(ActionEvent event) throws IOException, NexmoClientException  {
    	//PhoneMessage.sendMesg("+351962164544", "accepted");
    }

    @FXML
    void onStaffClicked(MouseEvent event) throws IOException {
    	SceneChanger.openWindow("views/LoginEmployee.fxml", new LoginInController(), event);
    }
    

    @FXML
    void onVolunteersPushed(ActionEvent event) {
    }

	
    

}
