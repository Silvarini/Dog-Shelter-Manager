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

// TODO: Auto-generated Javadoc
/**
 * The Class AdminPageController.
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

    /** The cl column. */
    @FXML
    private TableColumn<Dog, String> clColumn;

    /** The gw column. */
    @FXML
    private TableColumn<Dog, String> gwColumn;
    
    /** The state. */
    @FXML
    private TableColumn<Dog, String> state;
    
    
    

    /** The table employee. */
    @FXML
    private TableView<Employee> tableEmployee;

    /** The id E column. */
    @FXML
    private TableColumn<Employee, String> idEColumn;

    /** The name E column. */
    @FXML
    private TableColumn<Employee, String> nameEColumn;

    /** The birthdate E column. */
    @FXML
    private TableColumn<Employee, String> birthdateEColumn;

    /** The ph column. */
    @FXML
    private TableColumn<Employee, String> phColumn;

    /** The gender E column. */
    @FXML
    private TableColumn<Employee, String> genderEColumn;

    /** The un column. */
    @FXML
    private TableColumn<Employee, String> unColumn;

    /** The pw column. */
    @FXML
    private TableColumn<Employee, String> pwColumn;
	
    
    
    
	
    /** The historic table. */
    @FXML
    private TableView<State> historicTable;

    /** The id. */
    @FXML
    private TableColumn<State, String> id;

    /** The state H. */
    @FXML
    private TableColumn<State, String> stateH;

    /** The id D. */
    @FXML
    private TableColumn<State, String> idD;

    /** The id E. */
    @FXML
    private TableColumn<State, String> idE;

    /** The date H. */
    @FXML
    private TableColumn<State, String> dateH;
    
    
    
    
    
    /** The adoptions completed. */
    @FXML
    private TableView<Adoption> adoptionsCompleted;

    /** The adoption ID. */
    @FXML
    private TableColumn<Adoption, String> adoptionID;

    /** The guest. */
    @FXML
    private TableColumn<Adoption, String> guest;

    /** The dog ID. */
    @FXML
    private TableColumn<Adoption, String> dogID;

    /** The adoption state. */
    @FXML
    private TableColumn<Adoption, String> adoptionState;

    /** The date. */
    @FXML
    private TableColumn<Adoption, String> date;

    
    
	
    /**
     * CN employee pushed.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void CNEmployeePushed(ActionEvent event) throws IOException {
    	SceneChanger.openWindow("views/CreateEmployee.fxml",new NewEmployeeController(), event);
    }

    /**
     * Filter text.
     *
     * @param event the event
     */
    @FXML
    void FilterText(KeyEvent event) {

    }

    /**
     * Log out pushed.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void LogOutPushed(ActionEvent event) throws IOException {
    	SceneChanger.openWindow("views/LandingPage.fxml", new LandingPageController(), event);
    }

	/* (non-Javadoc)
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
