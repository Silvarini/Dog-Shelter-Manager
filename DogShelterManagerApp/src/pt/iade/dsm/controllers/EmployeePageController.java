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

public class EmployeePageController implements Initializable{

    @FXML
    private Label eNameLabel;

    @FXML
    private Label eIDLabel;

    @FXML
    private Label eGLabel;

    @FXML
    private ImageView photo;

    @FXML
    private TableView<Adoption> AdoptionRequests;

    @FXML
    private TableColumn<Adoption, String> idAColumn;

    @FXML
    private TableColumn<Adoption, String> guestColumn;

    @FXML
    private TableColumn<Adoption, String> idDColumn;

    @FXML
    private TableColumn<Adoption, String> stateAColumn;

    @FXML
    private TableColumn<Adoption, String> dateColumn;


    @FXML
    private TableView<Dog> Dog;

    @FXML
    private TableColumn<Dog, String> idColumn;

    @FXML
    private TableColumn<Dog, String> nameDColumn;

    @FXML
    private TableColumn<Dog, String> breedCOlumnn;

    @FXML
    private TableColumn<Dog, String> ageColumn;

    @FXML
    private TableColumn<Dog, String> stateDColumn;

    private static Adoption adoption;
    
    
    @FXML
    void logoutButton(ActionEvent event) throws IOException {
    	SceneChanger.openWindow("views/LandingPage.fxml", new LandingPageController(), event);
    }
    
    
    @FXML
    void InsertDogPushed(ActionEvent event) throws IOException {
    	SceneChanger.openWindow("views/AddingDog.fxml", new NewDogController(), event);

    } 
        
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


	public static Adoption getAdoption() {
		return adoption;
	}


	public void setAdoption(Adoption adoption) {
		EmployeePageController.adoption = adoption;
	}

}