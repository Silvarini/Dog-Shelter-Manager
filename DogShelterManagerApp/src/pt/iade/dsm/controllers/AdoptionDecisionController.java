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
//import pt.iade.dsm.communication.EmailSend;
//import pt.iade.dsm.communication.EmailSend;
//import pt.iade.dsm.comunication.PhoneMessage;
import pt.iade.dsm.models.Adoption;
import pt.iade.dsm.models.Dog;
import pt.iade.dsm.models.Employee;
import pt.iade.dsm.models.Guest;
import pt.iade.dsm.models.Historic;
import pt.iade.dsm.models.StateAdoption;
import pt.iade.dsm.models.StateDog;

/**
 * This class is the controller for the adoption pop up on the employee's page 
 * where the information and the behavior of the interface get manipulated.
 * Its a pop-up on the employee's page that shows when a cell, from the adoption requests table, is selected.
 * In this page the employee can accept or reject adoption requests.
 */


public class AdoptionDecisionController implements Initializable{
	
	/** The obs L. */
	/*Observation TextBox*/
	@FXML
    private Text obsL;
	
	/** The decision CB. */
	/*The employee's decision ComboBox*/
    @FXML
    private ComboBox<StateAdoption> decisionCB;
    
    /** The guest info L. */
    /*Informations about the guest*/
    @FXML
    private Label guestInfoL;
    
    /** The dog details L. */
    /*The details about the dog*/
    @FXML
    private Label dogDetailsL;
    
    /** The submit L. */
    /*Submit results Button*/
    @FXML
    private Label submitL;
    
    /** The dog C. */
    /*Dog that the guest wants to adopt*/
    private Dog dogC = null;
    
    /** The new state. */
    /*The new state of the dog after employee's decision*/
    private Historic newState = null;
    
    /** The selected adoption. */
    /*Object Adoption*/
    private Adoption selectedAdoption = null;
    

    /** The employee. */
    /*Object Employee*/
	private Employee employee = null;

   /**
    * Instantiates a new adoption decision controller.
    *
    * @param selectedAdoption the selected adoption
    * @param employee the employee
    */
   /*This controller's constructor, to pass information to Adoption and Employee's objects */
	 public AdoptionDecisionController(Adoption selectedAdoption, Employee employee) {
		 this.selectedAdoption = selectedAdoption;
		 this.employee = employee;
		}


	/**
	 * On dog profile pushed.
	 *
	 * @param event the event
	 */
	/*Opens the dog's profile*/
    @FXML
    void onDogProfilePushed(ActionEvent event) {
    	PopUpDisplayer.showPopupWindow("views/DogProfile.fxml", new DogProfileController(dogC));
    }
    
    /**
     * On ok pushed.
     *
     * @param event the event
     * @throws SQLException the SQL exception
     */
    /*This Button sets the values from the decision*/
    @FXML
    void onOkPushed(ActionEvent event) throws SQLException {
    	selectedAdoption.setState(decisionCB.getValue());
    	
    	/*Condition that sets the dog's state into adopted, if the decision was accepted.*/
    	if(selectedAdoption.getState().getAdoption().equals("accepted")) {

    	    /*Sets the dog's state into adopted*/
    		dogC.setState(new StateDog(2, "adopted"));

    	    /*This DAO's update the state of the adoption request and the dog.*/
        	AdoptionRequestDAO.decisionUpload(selectedAdoption, new StateAdoption(2,"accepted"));
        	DogDAO.changeDogState(dogC,new StateDog(2, "adopted") );

            /*Inserts into the historic table the new information*/
            newState = new Historic(new StateDog(2, "adopted"), dogC.getId(),employee.getEmployeeID());
        	HistoricDAO.insertStateDB(newState, new StateDog(2, "adopted"));
        			
       		
        	
        submitL.setText("Process completed.");
    }

        /*Condition that sets the adoption's request as rejected*/
    	else if(selectedAdoption.getState().getAdoption().equals("rejected")) {

    	    /*Updates the adoption request's state into rejected*/
    	AdoptionRequestDAO.decisionUpload(selectedAdoption, new StateAdoption(3, "rejected"));
    	 submitL.setText("Process completed.");
    	
    }
  
    } 
        
    /**
     * Initialize.
     *
     * @param location the location
     * @param resources the resources
     */
    /*This method gets the values needed from guest and dog for the interface.
     * 
     * Gives values to the page's elements.
     * */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	    /*Inserts the states from StateAdoption's table into the choice box.*/
		decisionCB.setItems(StateAdoption.getDecisions());
		
		submitL.setText("");

	    /*Selects the guest from the Guest table
	     * Inserts his information in the elements of the page
	     * */
				Guest guest = null;
				try {
					guest = GuestDAO.VerifyGuest(selectedAdoption.getGuestID());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				guestInfoL.setText(guest.getFirstName()+" "+guest.getLastName()+" | "+guest.getPhone()+" | "+guest.getEmail());
				obsL.setText(guest.getFirstName()+": "+guest.getObs());
				
				/*Selects a specific dog from Dog table
			     * Inserts his information in the elements of the page
			     * */
				Dog dog=null;
				try {
					dog = DogDAO.getSpecificDog(selectedAdoption.getDogID());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				dogDetailsL.setText(dog.getName()+" | "+dog.getBreed()+" | ID #"+dog.getId());
				DogsListController.setDog(dog);
				dogC=dog;	
				
				
		
	}
			



}