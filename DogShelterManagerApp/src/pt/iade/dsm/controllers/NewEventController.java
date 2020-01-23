package pt.iade.dsm.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
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
import jfxtras.scene.control.LocalDateTimePicker;
import pt.iade.dsm.DAO.EventDAO;
import pt.iade.dsm.models.Employee;
import pt.iade.dsm.models.Event;



/**
 * This class is the new event's page controller.
 */
public class NewEventController implements Initializable{
	 

	/*Calendar*/
    @FXML
    private LocalDateTimePicker calendar;

    /*Description*/
    @FXML
    private TextField descTextfield;

    /*Title*/
    @FXML
    private TextField titleTextfield;

    /*Number Participants*/
    @FXML
    private TextField partTextfield;

    /*Error Message*/
    @FXML
    private Label errorMsgLabel;
    
    /*Employee object*/
    private Employee employee;

    /**
     * Instantiates a new new event controller.
     *
     * @param employee the employee
     */
    public NewEventController(Employee employee) {
    	this.employee=employee;
    }


    /**
     * On finish pressed.
     *
     * @param event the event
     */
    /*This button inserts the new event's information*/
	@FXML
    void onFinishPressed(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation");
    	alert.setHeaderText("Confirm");
    	alert.setContentText("Do you want to submit the information?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	try 
    	{
    		/*If the day selected for the event is in least 2 days from the current date, it sends an error.*/
    		if(Period.between(LocalDate.now(), calendar.getLocalDateTime().toLocalDate()).getDays()<2) {
    			errorMsgLabel.setText("Events can only begin 2 days from now.");
    		}
    		else {
    			/*Inserts the new event's values*/
    		Event eve = new Event(employee.getEmployeeID(), titleTextfield.getText(), calendar.getLocalDateTime().toLocalDate(), descTextfield.getText(), Integer.valueOf(partTextfield.getText()));
    		EventDAO.insertEventIntoDB(eve);
		
    		SceneChanger.openWindow("views/EmployeePage.fxml", new EmployeePageController(employee), event);
    		}
    	}
    	catch(Exception e) {
    		errorMsgLabel.setText(e.getMessage());	
    	
    	}
    	
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
	errorMsgLabel.setText("");
	}

	
	}
	
	
	
	
		
	
	

