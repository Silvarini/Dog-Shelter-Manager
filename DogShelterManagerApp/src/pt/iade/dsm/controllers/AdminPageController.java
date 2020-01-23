package pt.iade.dsm.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.iade.dsm.DAO.AdoptionRequestDAO;
import pt.iade.dsm.DAO.DogDAO;
import pt.iade.dsm.DAO.EmployeeDAO;
import pt.iade.dsm.DAO.HistoricDAO;
import pt.iade.dsm.models.Adoption;
import pt.iade.dsm.models.Dog;
import pt.iade.dsm.models.Employee;
import pt.iade.dsm.models.EmployeePosition;
import pt.iade.dsm.models.Gender;
import pt.iade.dsm.models.Historic;

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

    /** The birthday Employee column. */
    @FXML
    private TableColumn<Employee, String> birthdateEColumn;

    /**  The  column position held by the Employee. */
    @FXML
    private TableColumn<Employee, EmployeePosition> phColumn;

    /** The gender Employee column. */
    @FXML
    private TableColumn<Employee, Gender> genderEColumn;

    /** The username column. */
    @FXML
    private TableColumn<Employee, String> unColumn;

    /** The password column. */
    @FXML
    private TableColumn<Employee, String> pwColumn;
	
    
    
    /** The historic table. */
    @FXML
    private TableView<Historic> historicTable;

    /**  The column id. */
    @FXML
    private TableColumn<Historic, String> id;

    /**  The column state of the Dog. */
    @FXML
    private TableColumn<Historic, String> stateH;

    /**  The column id Dog. */
    @FXML
    private TableColumn<Historic, String> idD;

    /**  The id Employee. */
    @FXML
    private TableColumn<Historic, String> idE;

    /**  The date of changes. */
    @FXML
    private TableColumn<Historic, String> dateH;
    
    
        
    /** The table adoptions completed. */
    @FXML
    private TableView<Adoption> adoptionsProcess;

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

    
    /** The filter. */
    @FXML
    private TextField filter;

    
    /**
     * Button that opens employee creation page.
     *
     * @param event the event
     */
    @FXML
    void CNEmployeePushed(ActionEvent event)  {
    	try {
			SceneChanger.openWindow("views/CreateEmployee.fxml",new NewEmployeeController(), event);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     * Button, Returns to Landing Page.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void LogOutPushed(ActionEvent event) throws IOException {
    	SceneChanger.openWindow("views/LandingPage.fxml", new LandingPageController(), event);
    }

	/**
	 * Initialize.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	/* 
	 * Called to initialize a controller after its root element has been completely processed.
	 * 
	 * This method sets all value's directory for the table's cells and set the values that gets
	 * from the DAO classes.
	 * 
	 * There is a table with all the dogs, all the adoptions, a table with all employees and the historic
	 * of all state changes of dogs. 
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
		phColumn.setCellValueFactory(new PropertyValueFactory<Employee, EmployeePosition>("pos_held"));
		genderEColumn.setCellValueFactory(new PropertyValueFactory<Employee, Gender>("gender"));
		unColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));
		pwColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("password"));
		
		adoptionID.setCellValueFactory(new PropertyValueFactory<Adoption, String>("AdoptionID"));
		guest.setCellValueFactory(new PropertyValueFactory<Adoption, String>("guestID"));
		dogID.setCellValueFactory(new PropertyValueFactory<Adoption, String>("dogID"));
		adoptionState.setCellValueFactory(new PropertyValueFactory<Adoption, String>("state"));
		date.setCellValueFactory(new PropertyValueFactory<Adoption, String>("requestDate"));
		
		id.setCellValueFactory(new PropertyValueFactory<Historic, String>("id"));
		stateH.setCellValueFactory(new PropertyValueFactory<Historic, String>("state"));
		idD.setCellValueFactory(new PropertyValueFactory<Historic, String>("dogID"));
		idE.setCellValueFactory(new PropertyValueFactory<Historic, String>("employeeID"));
		dateH.setCellValueFactory(new PropertyValueFactory<Historic, String>("initialDate"));
		
		/*
		 * Loads all the dogs. 
		 * */
		
		try {
			tableDog.setItems(DogDAO.loadDogs());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		 * Loads all the adoptions.
		 * */

		try {
			adoptionsProcess.setItems(AdoptionRequestDAO.loadAdoptionRequests());
		} catch (SQLException e) {
			e.printStackTrace();
			}

		/*
		 * Loads all the state changes on dogs.
		 * */
		
		
		try {
			historicTable.setItems(HistoricDAO.loadState());
		} catch (SQLException e) {
			e.printStackTrace();
			}
	

		/*
		 * Loads all employees.
		 * */
		
		try {
			tableEmployee.setItems(EmployeeDAO.loadEmployees());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
				
		//Dog table filter
		try {
			FilteredList<Dog> filteredData = new FilteredList<>( DogDAO.loadDogs(), p -> true);
		

        // 2. Set the filter Predicate whenever the filter changes.
        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(dog -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(dog.getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches name.
                } else if (String.valueOf(dog.getBreed()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches breed.
                } else if (String.valueOf(dog.getGender()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches gender.
                } 
                

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Dog> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableDog.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        tableDog.setItems(sortedData);
        
	}catch (SQLException e) {
		e.printStackTrace();
	}
		
		//Employee table filter
		try {
			FilteredList<Employee> filteredData2 = new FilteredList<>( EmployeeDAO.loadEmployees(), p -> true);
		

        // 2. Set the filter Predicate whenever the filter changes.
        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData2.setPredicate(employee -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(employee.getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches name.
                } else if (String.valueOf(employee.getGender()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches gender.
                } else if (String.valueOf(employee.getEmployeeID()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches id.
                } 
                

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Employee> sortedData = new SortedList<>(filteredData2);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableEmployee.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        tableEmployee.setItems(sortedData);
        
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		

	}
}
