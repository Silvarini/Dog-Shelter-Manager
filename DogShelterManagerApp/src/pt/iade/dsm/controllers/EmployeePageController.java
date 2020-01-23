package pt.iade.dsm.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pt.iade.dsm.DAO.AdoptionRequestDAO;
import pt.iade.dsm.DAO.DogDAO;
import pt.iade.dsm.DAO.EventDAO;
import pt.iade.dsm.models.Adoption;
import pt.iade.dsm.models.Dog;
import pt.iade.dsm.models.Employee;
import pt.iade.dsm.models.Event;

/**
 * This class is the controller for the employee's page 
 *  
 * */ class EmployeePageController implements Initializable{

    /** The employee. */
    private Employee employee=null;


	/**
	 * Instantiates a new employee page controller.
	 *
	 * @param employee the employee
	 */
	public EmployeePageController(Employee employee) {
    	this.setEmployee(employee);	
    	}


	/** The employee name label. */
    @FXML
    private Label eNameLabel;

    /** The employee ID label. */
    @FXML
    private Label eIDLabel;

    /** The employee G label. */
    @FXML
    private Label eGLabel;

    /** The employee photo. */
    @FXML
    private ImageView photo;

    /** The table Adoption requests. */
    @FXML
    private TableView<Adoption> AdoptionRequests;

    /** The id Adoption column. */
    @FXML
    private TableColumn<Adoption, String> idAColumn;

    /** The guest column. */
    @FXML
    private TableColumn<Adoption, String> guestColumn;

    /** The id dog column. */
    @FXML
    private TableColumn<Adoption, String> idDColumn;

    /** The state adoption column. */
    @FXML
    private TableColumn<Adoption, String> stateAColumn;

    /** The date column. */
    @FXML
    private TableColumn<Adoption, String> dateColumn;


    /** The Dog. */
    @FXML
    private TableView<Dog> Dog;

    /** The id column. */
    @FXML
    private TableColumn<Dog, String> idColumn;

    /** The name dog column. */
    @FXML
    private TableColumn<Dog, String> nameDColumn;

    /** The breed class column. */
    @FXML
    private TableColumn<Dog, String> breedCOlumnn;

    /** The age column. */
    @FXML
    private TableColumn<Dog, String> ageColumn;

    /** The state dog column. */
    @FXML
    private TableColumn<Dog, String> stateDColumn;
    
    /** The Events. */
    @FXML
    private TableView<Event> Events;

    /** The id. */
    @FXML
    private TableColumn<Event, Integer> id;

    /** The title. */
    @FXML
    private TableColumn<Event, String> title;

    /** The date. */
    @FXML
    private TableColumn<Event, LocalDate> date;

    /** The Nmax. */
    @FXML
    private TableColumn<Event, Integer> Nmax;

    /** The Nvol. */
    @FXML
    private TableColumn<Event, Integer> Nvol;


    /** The adoption. */
    private static Adoption adoption;
    
    /** The dog. */
    private static Dog dog;
    
    
    /**
     * This method return to the landing page.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void logoutButton(ActionEvent event) throws IOException {
    	SceneChanger.openWindow("views/LandingPage.fxml", new LandingPageController(), event);
    }
    
    
    /**
     * This method opens the scene where a new dog gets created.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void InsertDogPushed(ActionEvent event) throws IOException {
    	SceneChanger.openWindow("views/AddingDog.fxml", new NewDogController(employee), event);

    } 
    
    /**
     * This method opens the scene where events are created.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void onCreateEventPressed(ActionEvent event) throws IOException {
    	SceneChanger.openWindow("views/CreateEvent.fxml", new NewEventController(employee), event);
    }
        
    
    
    /**
     * This method opens the pop up where the employee changes the adoption state 
     * from a selected adoption request from the table.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void onAdoptionSelected(MouseEvent event) throws IOException {
    	
    	setAdoption(AdoptionRequests.getSelectionModel().getSelectedItem());
    	PopUpDisplayer.showPopupWindow("views/AdoptionConfirmation.fxml", new AdoptionDecisionController(adoption, employee));
    	AdoptionRequests.getItems().clear();
    	Dog.getItems().clear();
    	
    	/*Loads all adoptions that are in the state of "on hold", in the table view of AdoptionRequests*/
    	try {
			AdoptionRequests.setItems(AdoptionRequestDAO.loadAdoptionRequestsOnHold());
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	/*Loads all dogs, in the table view of Dog*/
    	try {
			Dog.setItems(DogDAO.loadDogs());
		} catch (SQLException e) {
			e.printStackTrace();
		}


    }
    
    /**
     * On dog selected.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    /*Mouse event, when clicked, opens the dog's edit page.
     * This method has a condition that doesn't open the page if the dog's state is "dead"
     */
    @FXML
    void onDogSelected(MouseEvent event) throws IOException {
    	setDog(Dog.getSelectionModel().getSelectedItem());
    	if(dog.getState().getState().equals("dead"))
    	    throw new IllegalArgumentException("Cannot edit a dead dog.");
    	else
    	SceneChanger.openWindow("views/EditDog.fxml", new EditDogController(dog, employee), event);
    }
    


	/**
	 * Called to initialize a controller after its root element has been completely processed.
	 * 
	 * This method sets all value's directory for the table's cells and set the values that gets
	 * from the DAO classes.
	 * 
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		eNameLabel.setText(getEmployee().getName());
		eIDLabel.setText(String.valueOf(getEmployee().getEmployeeID()));
		eGLabel.setText(getEmployee().getGender().getGender());
		
		
		idAColumn.setCellValueFactory(new PropertyValueFactory<Adoption, String>("AdoptionID"));
		guestColumn.setCellValueFactory(new PropertyValueFactory<Adoption, String>("guestID"));
		idDColumn.setCellValueFactory(new PropertyValueFactory<Adoption, String>("dogID"));
		stateAColumn.setCellValueFactory(new PropertyValueFactory<Adoption, String>("state"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Adoption, String>("requestDate"));
		
		
		idColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("id"));
		nameDColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("name"));
		breedCOlumnn.setCellValueFactory(new PropertyValueFactory<Dog, String>("breed"));
		ageColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("age"));
		stateDColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("state"));
		
		
		id.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));
		title.setCellValueFactory(new PropertyValueFactory<Event, String>("title"));
		date.setCellValueFactory(new PropertyValueFactory<Event, LocalDate>("eventDate"));
		Nmax.setCellValueFactory(new PropertyValueFactory<Event, Integer>("maxPart"));
		Nvol.setCellValueFactory(new PropertyValueFactory<Event, Integer>("currentPart"));
		
		try {
			Events.getItems().addAll(EventDAO.loadEvents());
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		try {
			AdoptionRequests.getItems().addAll(AdoptionRequestDAO.loadAdoptionRequestsOnHold());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Dog.getItems().addAll(DogDAO.loadDogs());
		} catch (SQLException e) {
			e.printStackTrace();
		}


		 try{
	            String imgLocation = "src/pt/iade/dsm/images/Employees/" + getEmployee().getPhoto().getName();
	            File imageFile = new File(imgLocation);
	            BufferedImage bufferedImage = ImageIO.read(imageFile);
	            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
	            photo.setImage(image);
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
		
		
	}


	/**
	 * Gets the adoption.
	 *
	 * @return the adoption.
	 */
	public static Adoption getAdoption() {
		return adoption;
	}


	/**
	 * Sets the adoption.
	 *
	 * @param adoption the new adoption.
	 */
	public void setAdoption(Adoption adoption) {
		EmployeePageController.adoption = adoption;
	}

	/**
	 * Gets the dog.
	 *
	 * @return the dog.
	 */
	public static Dog getDog() {
		return dog;
	}

	/**
	 * Sets the dog.
	 *
	 * @param dog the new dog.
	 */
	public static void setDog(Dog dog) {
		EmployeePageController.dog = dog;
	}

	/**
	 * Gets the employee.
	 *
	 * @return the employee.
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * Sets the employee.
	 *
	 * @param employee the new employee.
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}