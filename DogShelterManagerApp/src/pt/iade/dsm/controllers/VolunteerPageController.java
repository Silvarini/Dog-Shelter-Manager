package pt.iade.dsm.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import jfxtras.scene.control.LocalDatePicker;
import pt.iade.dsm.DAO.EventDAO;
import pt.iade.dsm.DAO.RegisterDAO;
import pt.iade.dsm.DAO.VolunteerDAO;
import pt.iade.dsm.models.Event;
import pt.iade.dsm.models.Registration;
import pt.iade.dsm.models.Volunteer;


/**
 * This class is the volunteer's page controller.
 */
public class VolunteerPageController implements Initializable {

    	/*Title label*/
	    @FXML
	    private Label title;
	    
    	/*Available*/
	    @FXML
	    private Label available;
	    
    	/*Max participants label*/
	    @FXML
	    private Label maxPart;
	    
    	/*Calendar*/
	    @FXML
	    private LocalDatePicker calendar;

    	/*Description*/
	    @FXML
	    private TextArea description;

    	/*Registration table view*/
	    @FXML
	    private TableView<Registration> registeredEvents;

    	/*EventID column*/
	    @FXML
	    private TableColumn<Registration, Integer> eventID;

    	/*InscriptionID column*/
	    @FXML
	    private TableColumn<Registration, Integer> insc_ID;

    	/*Title column*/
	    @FXML
	    private TableColumn<Registration, String> titleCl;

    	/*Date column*/
	    @FXML
	    private TableColumn<Registration, LocalDate> date;

    	/*VolunteerID column*/
	    @FXML
	    private Label idVolunteer;

    	/*Name label*/
	    @FXML
	    private Label name;

    	/*Email label*/
	    @FXML
	    private Label email;

    	/*Phone label*/
	    @FXML
	    private Label phone;

    	/*Address label*/
	    @FXML
	    private Label address;

    	/*Event from selected date table view*/
	    @FXML
	    private TableView<Event> eventsOnSelectedDate;

    	/*EventID column*/
	    @FXML
	    private TableColumn<Event, Integer> idE;

    	/*Title column*/
	    @FXML
	    private TableColumn<Event, String> tittleCl;

    	/*Time column*/
	    @FXML
	    private TableColumn<Event, LocalDate> TimeCl;
	    
    	/*Message*/
	    @FXML
	    private Label eMsg;
	    
    	/*Volunteer object*/
	    private Volunteer volunteer = LoginInVolunteer.getVolunteer();
	    
    	/*Event object*/
	    private Event eventC = null;
	    
	    /**
    	 * Sets the event C.
    	 *
    	 * @param eventC the new event C
    	 */
    	public void setEventC(Event eventC) {
			this.eventC = eventC;
		}


	    /**
    	 * On apply.
    	 *
    	 * @param event the event
    	 * @throws SQLException the SQL exception
    	 * @throws IOException Signals that an I/O exception has occurred.
    	 */
    	/*This button inserts registration's information*/
		@FXML
	    void onApply(ActionEvent event) throws SQLException, IOException {
			setEventC(eventC);
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Confirmation");
	    	alert.setHeaderText("Confirm");
	    	alert.setContentText("Do you want to submit the registration?");

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    		for(Event eve: EventDAO.loadEvents()) {
	    			/*Condition where the event is selected by id and the max participants is compared with the current number of participants.*/
					if(eve.getId() == eventC.getId()) {
						if(eve.getMaxPart() == eve.getCurrentPart())
							eMsg.setText("The event is full.");
						else {
							/*Verifies if the volunteer as already registered in this event*/
							if(VolunteerDAO.VerifyInsc(volunteer.getIdV(), eventC.getId())==null) {
								try 
								{	
									/*Inserts the new registration*/
									Registration reg = new Registration(volunteer.getIdV(), eventC.getId(), eventC.getTitle(), eventC.getEventDate());
									RegisterDAO.insertRegistrationIntoDB(reg);
									EventDAO.updatePart(eventC.getId());
	    		
									eMsg.setText("Registration complete.");
	    				
									registeredEvents.getItems().clear();
									available.setText("");
									available.setText(String.valueOf(eve.getMaxPart()-eve.getCurrentPart()));
								
	    		
								try {
									registeredEvents.getItems().addAll(RegisterDAO.loadRegistrations(volunteer.getIdV()));
								} catch (SQLException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								}	
							}
	    				catch(Exception e) {
	    				e.getMessage();	
	    				}
					}		
	    		else
	    			eMsg.setText("You aready registered to this event.");
	    		}
				}
		}
	    }
	    }
		
		/**
		 * On log out.
		 *
		 * @param event the event
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		/*Returns to LandingPage*/
		@FXML
	    void onLogOut(ActionEvent event) throws IOException {
			SceneChanger.openWindow("views/LandingPage.fxml", new LandingPageController(), event);
	    }
	    
	   
		/**
		 * On date choosed.
		 *
		 * @throws SQLException the SQL exception
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		/*Verifies if there is events in a chosen date.*/
	    @FXML
	    void onDateChoosed() throws SQLException, IOException {
	    				
	    	ObservableList<Event> events = FXCollections.observableArrayList();
	    	
	    	try {
	    	if(EventDAO.checkDates(Date.valueOf(calendar.getLocalDate()))!=null)
	    	{
	    		events.addAll(EventDAO.checkDates(Date.valueOf(calendar.getLocalDate())));
	    		eventsOnSelectedDate.getItems().clear();
	    	
	    		eventsOnSelectedDate.getItems().addAll(events);
	    	}
	    	else
	    		eventsOnSelectedDate.getItems().clear();
	    	
	    	}
	    	catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    }
	    
	    /**
    	 * On edit.
    	 *
    	 * @param event the event
    	 */
    	/*Opens volunteer edit page*/
	    @FXML
	    void onEdit(ActionEvent event) {
	    	PopUpDisplayer.showPopupWindow("views/EditVolunteer.fxml", new EditVolunteerControlller());
	        phone.setText(volunteer.getPhone());
	        address.setText(volunteer.getAdress());
	        email.setText(volunteer.getEmail());


	    }
	    
	    /**
    	 * On event choosed.
    	 *
    	 * @param event the event
    	 */
    	/*Inserts new event registration*/
	    @FXML
	    void onEventChoosed(MouseEvent event) {
	    	setEventC(eventsOnSelectedDate.getSelectionModel().getSelectedItem());
	    	if(eventC != null) {
	    	title.setText(eventC.getTitle());
	    	maxPart.setText(String.valueOf(eventC.getMaxPart()));
	    	available.setText(String.valueOf(eventC.getMaxPart()-eventC.getCurrentPart()));
	    	description.setText(eventC.getDescription());	
	    }
	    	else{
	    		title.setText("");
	    		available.setText("");
	    		maxPart.setText("");
	    		description.setText("");
	    	}
	    }
	    


		/**
		 * Called to initialize a controller after its root element has been completely processed.
		 * 
		 * This method sets all value's directory for the table's cells and set the values that gets
		 * from the DAO classes.
		 *
		 * @param arg0 the arg 0
		 * @param arg1 the arg 1
		 */
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {		
			
		title.setText("");
		available.setText("");
		maxPart.setText("");
		
		eMsg.setText("");
		
		idVolunteer.setText(String.valueOf(volunteer.getIdV()));
		name.setText(volunteer.getFirstName()+" "+volunteer.getLastName());
		email.setText(volunteer.getEmail());
		phone.setText(volunteer.getPhone());
		address.setText(volunteer.getAdress());
		
		idE.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));
		tittleCl.setCellValueFactory(new PropertyValueFactory<Event, String>("title"));
		TimeCl.setCellValueFactory(new PropertyValueFactory<Event, LocalDate>("eventDate"));
		
		eventsOnSelectedDate.getItems().add(null);
		
		eventID.setCellValueFactory(new PropertyValueFactory<Registration, Integer>("idevent"));
		insc_ID.setCellValueFactory(new PropertyValueFactory<Registration, Integer>("idinsc"));
		titleCl.setCellValueFactory(new PropertyValueFactory<Registration, String>("titleE"));
		date.setCellValueFactory(new PropertyValueFactory<Registration, LocalDate>("date"));
		
		try {
			registeredEvents.getItems().addAll(RegisterDAO.loadRegistrations(volunteer.getIdV()));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}

	
}
