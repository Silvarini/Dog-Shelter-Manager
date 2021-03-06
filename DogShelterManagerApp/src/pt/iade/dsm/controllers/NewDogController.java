package pt.iade.dsm.controllers;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.iade.dsm.DAO.DogDAO;
import pt.iade.dsm.DAO.HistoricDAO;
import pt.iade.dsm.models.AgeClass;
import pt.iade.dsm.models.Breed;
import pt.iade.dsm.models.CoatLength;
import pt.iade.dsm.models.Dog;
import pt.iade.dsm.models.Employee;
import pt.iade.dsm.models.Gender;
import pt.iade.dsm.models.GoodWith;
import pt.iade.dsm.models.Historic;
import pt.iade.dsm.models.Size;
import pt.iade.dsm.models.StateDog;

/**
 * This is the controller for the new dog's scene.
 */
public class NewDogController implements Initializable{

    /** The Dog name text field. */
    @FXML
    private TextField DogName_tf;

    /** The dog breed choice box. */
    @FXML
    private ChoiceBox<Breed> dogBreed;

    /** The dog gender choice box. */
    @FXML
    private ChoiceBox<Gender> dogGender;

    /** The dog size choice box. */
    @FXML
    private ChoiceBox<Size> dogSize;

    /** The dog coat length choice box. */
    @FXML
    private ChoiceBox<CoatLength> dogCL;

    /** The dog good with choice box. */
    @FXML
    private ChoiceBox<GoodWith> dogGw;

    /** The dog age choice box. */
    @FXML
    private ChoiceBox<AgeClass> dogAge;
    

    
    /** The Observation text field. */
    @FXML
    private TextField obsField;
    
    /** The save info message. */
    @FXML
    private Label saveInfo;
    
    /** The back page. */
    @FXML
    private Label backPage;
    
    /** The image view. */
    @FXML
    private ImageView imageView;
    
    /** The image file. */
    private File imageFile;
    
    /** The image file changed. */
    boolean imageFileChanged;
        
    /** The employee. */
    private Employee employee;
    
    
    /**
     * Instantiates a new new dog controller.
     *
     * @param employee the employee
     */
    public NewDogController(Employee employee) {
		this.employee = employee;
	}
    
    
    
    /**
     * This method changes scene into employee's page.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void onBackClicked(MouseEvent event) throws IOException {
    	SceneChanger.openWindow("views/EmployeePage.fxml", new EmployeePageController(employee), event);
    }
  
      
    /**
     * This method opens a pop up asking for confirmation.
     * If Ok was pushed, all values are saved into database.
     *
     * @param event the event
     */
    @FXML
    void SaveData(ActionEvent event)  {
    	    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation");
    	alert.setHeaderText("Confirm");
    	alert.setContentText("Do you want to submit the information?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	
    		/*Inserts the new dog's information in Dog and Historic table*/	
    	try 
    	{
    	Dog dog = new Dog(DogName_tf.getText(),dogBreed.getValue(), dogAge.getValue(),dogGender.getValue(),
    					  dogSize.getValue(),dogCL.getValue(),dogGw.getValue(),obsField.getText(),imageFile,new StateDog(4,"not adopted"));
    	
			Historic historic = new Historic(new StateDog(4,"not adopted"), DogDAO.insertDogIntoDB(dog,dogAge.getValue(),dogBreed.getValue(),dogCL.getValue(),dogGender.getValue(),dogSize.getValue(),dogGw.getValue(),new StateDog(4,"not adopted")), employee.getEmployeeID());	
			HistoricDAO.insertStateDB(historic, new StateDog(4,"not adopted"));	
		
		
		SceneChanger.openWindow("views/EmployeePage.fxml", new EmployeePageController(employee), event);
		}
    	catch (Exception e){
    		saveInfo.setText(e.getMessage());	
    	}
    	}
    }

    


	/**
     * On upload photo.
     *
     * @param event the event
     */
    @FXML
    void onUploadPhoto(ActionEvent event) {
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
    	                    imageView.setImage(img);
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
     * @param location the location
     * @param resources the resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){ 	
    	
    	
    //Show default image for Dog	
    try {
    	 imageFile = new File("src/pt/iade/dsm/images/DefaultDog.png");
         BufferedImage bufferedImage = ImageIO.read(imageFile);
         Image image = SwingFXUtils.toFXImage(bufferedImage, null);
         imageView.setImage(image);
    	}
    
    catch(IOException e)
    {
        System.err.println(e.getMessage());
    	
    }
    
    
    	saveInfo.setText("");
    	
    	/*
    	 * Setting values for the goodWith, age, breed, size, gender and coatLength.
    	 * 
    	 * */
    	
    	dogGw.setItems(GoodWith.loadGoodWith());
		
		dogAge.setItems(AgeClass.loadAgeClass());
		
		dogBreed.setItems(Breed.loadBreeds());
		
		dogSize.setItems(Size.loadSizes());
		
		dogGender.setItems(Gender.getGenders());
		
		dogCL.setItems(CoatLength.loadCoatLengths());
		
		
		
		
		
	}


}
