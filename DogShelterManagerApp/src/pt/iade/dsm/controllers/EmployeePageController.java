package pt.iade.dsm.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import pt.iade.dsm.models.Adoption;
import pt.iade.dsm.models.Dog;

/**
 * This class is the controller for the employee's page 
 *  
 * */ class EmployeePageController implements Initializable{

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

    /** The adoption. */
    private static Adoption adoption;
    
    
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
    	SceneChanger.openWindow("views/AddingDog.fxml", new NewDogController(), event);

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
    	PopUpDisplayer.showPopupWindow("views/AdoptionConfirmation.fxml", new AdoptionDecisionController());
    	AdoptionRequests.getItems().clear();
    	Dog.getItems().clear();
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
		eNameLabel.setText(LoginInController.getEmployee().getName());
		eIDLabel.setText(String.valueOf(LoginInController.getEmployee().getEmployeeID()));
		eGLabel.setText(LoginInController.getEmployee().getGender());
		
		
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
	            String imgLocation = "src/pt/iade/dsm/images/Employees/" + LoginInController.getEmployee().getPhoto().getName();
	            File imageFile = new File(imgLocation);
	            BufferedImage bufferedImage = ImageIO.read(imageFile);
	            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
	            photo.setImage(image);
	        }
	        catch (IOException e)
	        {
	            System.err.println(e.getMessage());
	        }
		
		
	}


	/**
	 * Gets the adoption.
	 *
	 * @return the adoption
	 */
	public static Adoption getAdoption() {
		return adoption;
	}


	/**
	 * Sets the adoption.
	 *
	 * @param adoption the new adoption
	 */
	public void setAdoption(Adoption adoption) {
		EmployeePageController.adoption = adoption;
	}

}