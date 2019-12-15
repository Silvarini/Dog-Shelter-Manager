package pt.iade.dsm.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import pt.iade.dsm.DAO.AdoptionRequestDAO;
import pt.iade.dsm.DAO.DogDAO;
import pt.iade.dsm.DAO.EmployeeDAO;
import pt.iade.dsm.DAO.HistoricDAO;
import pt.iade.dsm.models.Adoption;
import pt.iade.dsm.models.Dog;
import pt.iade.dsm.models.Employee;
import pt.iade.dsm.models.State;

/**
 * This class is the controller for the admin page 
 * where the information and the behavior of the interface
 * get manipulated.
 */
public class AdminPageController implements Initializable{

	/** The table dog. */
	@FXML
    private TableView<Dog> tableDog;

    /** The id column. */
    @FXML
    private TableColumn<Dog, String> idColumn;

    /** The name column. */
    @FXML
    private TableColumn<Dog, String> nameColumn;

    /** The age column. */
    @FXML
    private TableColumn<Dog, String> ageColumn;

    /** The gender column. */
    @FXML
    private TableColumn<Dog, String> genderColumn;

    /** The breed column. */
    @FXML
    private TableColumn<Dog, String> breedColumn;

    /** The size column. */
    @FXML
    private TableColumn<Dog, String> sizeColumn;

    /** The coat length column. */
    @FXML
    private TableColumn<Dog, String> clColumn;

    /** The good with column. */
    @FXML
    private TableColumn<Dog, String> gwColumn;
    
    /** The state column. */
    @FXML
    private TableColumn<Dog, String> state;
    
    
    

    /** The table employee. */
    @FXML
    private TableView<Employee> tableEmployee;

    /** The id Employee column. */
    @FXML
    private TableColumn<Employee, String> idEColumn;

    /** The name Employee column. */
    @FXML
    private TableColumn<Employee, String> nameEColumn;

    /** The birthdate Employee column. */
    @FXML
    private TableColumn<Employee, String> birthdateEColumn;

    /** The  column position held by the Employee */
    @FXML
    private TableColumn<Employee, String> phColumn;

    /** The gender Employee column. */
    @FXML
    private TableColumn<Employee, String> genderEColumn;

    /** The username column. */
    @FXML
    private TableColumn<Employee, String> unColumn;

    /** The password column. */
    @FXML
    private TableColumn<Employee, String> pwColumn;
	
    
    
    
	
    /** The historic table. */
    @FXML
    private TableView<State> historicTable;

    /** The column id   */
    @FXML
    private TableColumn<State, String> id;

    /** The column state of the Dog  */
    @FXML
    private TableColumn<State, String> stateH;

    /** The column id Dog */
    @FXML
    private TableColumn<State, String> idD;

    /** The id Employee */
    @FXML
    private TableColumn<State, String> idE;

    /** The date of changes */
    @FXML
    private TableColumn<State, String> dateH;
    
    
    
    
    
    /** The table adoptions completed. */
    @FXML
    private TableView<Adoption> adoptionsCompleted;

    /** The column adoption ID. */
    @FXML
    private TableColumn<Adoption, String> adoptionID;

    /** The column guest. */
    @FXML
    private TableColumn<Adoption, String> guest;

    /** The column dog ID. */
    @FXML
    private TableColumn<Adoption, String> dogID;

    /** The column adoption state. */
    @FXML
    private TableColumn<Adoption, String> adoptionState;

    /** The column date. */
    @FXML
    private TableColumn<Adoption, String> date;

    
    
	
    /**
     * Button that opens employee creation page.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void CNEmployeePushed(ActionEvent event) throws IOException {
    	SceneChanger.openWindow("views/CreateEmployee.fxml",new NewEmployeeController(), event);
    }

    /**
     * Filter results.
     * Still in development.
     *
     * @param event the event
     */
    @FXML
    void FilterText(KeyEvent event) {

    }

    /**
     * Button to Log out.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void LogOutPushed(ActionEvent event) throws IOException {
    	SceneChanger.openWindow("views/LandingPage.fxml", new LandingPageController(), event);
    }

	/* 
	 * Called to initialize a controller after its root element has been completely processed.
	 * 
	 * This method sets all value's directory for the table's cells and set the values that gets
	 * from the DAO classes.
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		idColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("name"));
		ageColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("age"));
		genderColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("gender"));
		breedColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("breed"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("size"));
		clColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("coat"));
		gwColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("goodw"));
		state.setCellValueFactory(new PropertyValueFactory<Dog, String>("state"));
		
		idEColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeID"));
		nameEColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		birthdateEColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("birthdate"));
		phColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("pos_held"));
		genderEColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("gender"));
		unColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));
		pwColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("password"));
		
		adoptionID.setCellValueFactory(new PropertyValueFactory<Adoption, String>("AdoptionID"));
		guest.setCellValueFactory(new PropertyValueFactory<Adoption, String>("guestID"));
		dogID.setCellValueFactory(new PropertyValueFactory<Adoption, String>("dogID"));
		adoptionState.setCellValueFactory(new PropertyValueFactory<Adoption, String>("state"));
		date.setCellValueFactory(new PropertyValueFactory<Adoption, String>("requestDate"));
		
		id.setCellValueFactory(new PropertyValueFactory<State, String>("id"));
		stateH.setCellValueFactory(new PropertyValueFactory<State, String>("state"));
		idD.setCellValueFactory(new PropertyValueFactory<State, String>("dogID"));
		idE.setCellValueFactory(new PropertyValueFactory<State, String>("employeeID"));
		dateH.setCellValueFactory(new PropertyValueFactory<State, String>("initialDate"));
		
		try {
			tableDog.getItems().addAll(DogDAO.loadDogs());
		} catch (SQLException e) {
			e.printStackTrace();
			}
		
		
		try {
				tableEmployee.getItems().addAll(EmployeeDAO.loadEmployees());
		} catch (IOException e) {
				e.printStackTrace();
		} catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		try {
			adoptionsCompleted.getItems().addAll(AdoptionRequestDAO.loadAdoptionRequests());
		} catch (SQLException e) {
			e.printStackTrace();
			}
		 
		
		try {
			historicTable.getItems().addAll(HistoricDAO.loadState());
		} catch (SQLException e) {
			e.printStackTrace();
			}
	
	
	}

}
