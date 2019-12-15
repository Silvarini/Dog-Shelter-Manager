package pt.iade.dsm.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import pt.iade.dsm.DAO.AdoptionRequestDAO;
import pt.iade.dsm.DAO.DogDAO;
import pt.iade.dsm.DAO.GuestDAO;
import pt.iade.dsm.DAO.HistoricDAO;
import pt.iade.dsm.communication.EmailSend;
//import pt.iade.dsm.comunication.PhoneMessage;
import pt.iade.dsm.models.Adoption;
import pt.iade.dsm.models.Dog;
import pt.iade.dsm.models.Guest;
import pt.iade.dsm.models.State;

public class AdoptionDecisionController implements Initializable{

	@FXML
    private Text obsL;

    @FXML
    private ComboBox<String> decisionCB;

    @FXML
    private Label guestInfoL;

    @FXML
    private Label dogDetailsL;
    
    @FXML
    private Label submitL;
    
    
    private Guest guestC = null; 
    
    
    private Dog dogC = null;
    
    
    private State newState = null;
    
    
    private Adoption selectedAdoption = EmployeePageController.getAdoption();

       
    

    @FXML
    void onDogProfilePushed(ActionEvent event) {
    	PopUpDisplayer.showPopupWindow("views/DogProfile.fxml", new DogProfileController());
    }

    @FXML
    void onOkPushed(ActionEvent event) throws SQLException {
    	selectedAdoption.setState(decisionCB.getValue());
    	
    	
    	if(selectedAdoption.getState().equals("accepted")) {
    		dogC.setState("adopted");
    		
        	AdoptionRequestDAO.decisionUpload(selectedAdoption);
        	DogDAO.changeDogState(dogC);
        	
            newState = new State("adopted", dogC.getId(), LoginInController.getEmployee().getEmployeeID());
        	HistoricDAO.insertStateDB(newState);
        			
       		
        	
        submitL.setText("Process completed.");
        System.out.println(newState.getDogID()+" "+newState.getState());
    }
    	
    	else if(selectedAdoption.getState().equals("rejected")) {
    	AdoptionRequestDAO.decisionUpload(selectedAdoption);
    	 submitL.setText("Process completed.");
    	
    }
    	EmailSend.sendEmail(guestC.getEmail(), selectedAdoption.getState());
    	//PhoneMessage.sendMsg(guestC.getPhone());
    } 
        
        
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		decisionCB.getItems().add("accepted");
		decisionCB.getItems().add("rejected");
		
		submitL.setText("");
		
			try {
				for (Guest guest: GuestDAO.loadGuest()) {
				if(selectedAdoption.getGuestID().equals(guest.getPhone())) {
					guestInfoL.setText(guest.getFirstName()+" "+guest.getLastName()+" | "+guest.getPhone()+" | "+guest.getEmail());
					obsL.setText(guest.getFirstName()+": "+guest.getObs());
					guestC = guest;
				}
				}
				for (Dog dog: DogDAO.loadDogs()) {
					
				if(selectedAdoption.getDogID()==dog.getId()) {
					dogDetailsL.setText(dog.getName()+" | "+dog.getBreed()+" | ID #"+dog.getId());
					DogsListController.setDog(dog);
					dogC=dog;
				}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
			



}