package pt.iade.dsm.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.iade.dsm.DAO.DogDAO;
import pt.iade.dsm.DAO.HistoricDAO;
import pt.iade.dsm.models.AgeClass;
import pt.iade.dsm.models.CoatLength;
import pt.iade.dsm.models.Dog;
import pt.iade.dsm.models.Employee;
import pt.iade.dsm.models.Historic;
import pt.iade.dsm.models.Size;
import pt.iade.dsm.models.StateDog;




/*
 * This class is where the Employee can edit the dog's information.
 * 
 * 
 * */

/**
 * The Class EditDogController.
 */
public class EditDogController implements Initializable {
	
	/** The photo IV. */
	/*Dog's photo*/
	@FXML
    private ImageView photoIV;
	
	/** The size CB. */
	/*Size choice box*/
	@FXML
    private ChoiceBox<Size> sizeCB;
	
    /** The coat CB. */
    /*Coat length choice box*/ 
	@FXML
    private ChoiceBox<CoatLength> coatCB;

	/** The state CB. */
	/*The dog's state choice box*/
    @FXML
    private ChoiceBox<StateDog> stateCB;

    /** The age CB. */
    /*The dog's age class choice box*/
    @FXML
    private ChoiceBox<AgeClass> ageCB;

    /** The obs text. */
    /*Observation box*/
    @FXML
    private TextArea obsText;

    /** The name L. */
    /*Dog's name*/
    @FXML
    private Label nameL;

    /** The id L. */
    /*Dog's id*/
    @FXML
    private Label idL;

    /** The gender L. */
    /*Dog's gender*/
    @FXML
    private Label genderL;
    
    /** The e MSG. */
    /*Messages of interaction with user*/
    @FXML
    private Label eMSG;
    
    /** The image file. */
    /*Image*/
    private File imageFile;
    
    /** The image file changed. */
    /*Condition of image changing*/
    boolean imageFileChanged;
    
    /** The e dog. */
    /*Dog object*/
    private Dog eDog;
    
    /** The employee. */
    /*Employee object*/
    private Employee employee;

    /**
     * Instantiates a new edits the dog controller.
     *
     * @param eDog the e dog
     * @param employee the employee
     */
    /*The controller's constructor*/
	public EditDogController(Dog eDog, Employee employee) {
		this.eDog = eDog;
		this.employee = employee;
	}

    
    /**
     * On submit pushed.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    /*Button, submits the edited information of the dog*/
    @FXML
    void onSubmitPushed(ActionEvent event) throws IOException {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation");
    	alert.setHeaderText("Confirm");
    	alert.setContentText("Do you want to submit the information?");

    	Optional<ButtonType> result = alert.showAndWait();
    	/*Sets the new edited values*/
    	if (result.get() == ButtonType.OK){
    		eDog.setAge(ageCB.getValue());
    		eDog.setCoat(coatCB.getValue());
    		eDog.setObs(obsText.getText());
    		eDog.setPhoto(imageFile);
    		eDog.setSize(sizeCB.getValue());
    		
    		/*If the new dog's state isn't the same as before*/
    		if(eDog.getState()!=stateCB.getValue() && !stateCB.getValue().getState().equals("")) {
    			
    			/*If the dog's new state is "returned", but he was never adopted
    			 * Or when the new dog's state is "dead", when he is already adopted
    			 */
    		    if(eDog.getState().getState().equals("not adopted") && stateCB.getValue().getState().equals("returned") || eDog.getState().getState().equals("adopted") && stateCB.getValue().getState().equals("dead"))
    		    	eMSG.setText("Error 912 \n Caused by: Invalid state.");
    		    else {
    		    	/*Inserts the new information of the dog's state change in historic table*/
    		    	eDog.setState(stateCB.getValue());
    		    	Historic newState = new Historic(eDog.getState(), eDog.getId(), employee.getEmployeeID());
    		    	try {
    		    		HistoricDAO.insertStateDB(newState, new StateDog(eDog.getState().getStateID(), eDog.getState().getState()));
    		    	} catch (SQLException e) {
    		    		e.printStackTrace();
    		    	}
    		    	try {
    		    		/*Updates the new information in the Dog's table*/
    		    		DogDAO.updateDog(eDog,sizeCB.getValue(), coatCB.getValue(),ageCB.getValue(),stateCB.getValue());
    		    	} catch (SQLException e) {
    		    		eMSG.setText(e.getMessage());
    		    	}	
    		    SceneChanger.openWindow("views/EmployeePage.fxml", new EmployeePageController(employee), event);	
    		    }
    		}
    	}	
    }
    
    /**
     * On upload pushed.
     *
     * @param event the event
     */
    @FXML
    void onUploadPushed(ActionEvent event) {
    	//get the Stage to open a new window (or Stage in JavaFX)
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        //Instantiate a FileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        
        //filter for .jpg and .png
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("Image File (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("Image File (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter);
        
        //Set to the user's picture directory or user directory if not available
        String userDirectoryString = System.getProperty("user.home")+"\\Pictures";
        File userDirectory = new File(userDirectoryString);
        
        //if you cannot navigate to the pictures directory, go to the user home
        if (!userDirectory.canRead())
            userDirectory = new File(System.getProperty("user.home"));
        
        fileChooser.setInitialDirectory(userDirectory);
        
        //open the file dialog window
        File tmpImageFile = fileChooser.showOpenDialog(stage);
        
        if (tmpImageFile != null)
        {
            imageFile = tmpImageFile;
        
            //update the ImageView with the new image
            if (imageFile.isFile())
            {
                try
                {
                    BufferedImage bufferedImage = ImageIO.read(imageFile);
                    Image img = SwingFXUtils.toFXImage(bufferedImage, null);
                    photoIV.setImage(img);
                    imageFileChanged = true;
                }
                catch (IOException e)
                {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
    
    /**
     * Initialize.
     *
     * @param arg0 the arg 0
     * @param arg1 the arg 1
     */
    /*Initializes this controller's page
     * Sets all elements of the page with values*/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		nameL.setText(eDog.getName());
		genderL.setText(eDog.getGender().getGender());
		idL.setText(String.valueOf(eDog.getId()));
		
		obsText.setText(eDog.getObs());
		
		eMSG.setText("");
		 try{
	            String imgLocation = "src/pt/iade/dsm/images/Dogs/" + eDog.getPhoto().getName();
	            File imageFile = new File(imgLocation);
	            BufferedImage bufferedImage = ImageIO.read(imageFile);
	            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
	            photoIV.setImage(image);
	        }
	        catch (IOException e)
	        {
	            System.err.println(e.getMessage());
	        }
		
		 /*The following represent the age, size, coat length and state's vales of the choice boxes*/
			ageCB.setItems(AgeClass.loadAgeClass());
			ageCB.setValue(eDog.getAge());
			
			System.out.println(eDog.getAge());
			
		
			sizeCB.setItems(Size.loadSizes());
			sizeCB.setValue(eDog.getSize());
			
			coatCB.setItems(CoatLength.loadCoatLengths());
			coatCB.setValue(eDog.getCoat());
			
			stateCB.setItems(StateDog.getStatesHouseDogs());
			stateCB.setValue(eDog.getState());
	}

	
}
