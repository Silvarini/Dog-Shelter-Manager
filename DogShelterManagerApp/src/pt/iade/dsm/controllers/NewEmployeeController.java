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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.iade.dsm.DAO.EmployeeDAO;
import pt.iade.dsm.models.Employee;
import pt.iade.dsm.models.EmployeePosition;
import pt.iade.dsm.models.Gender;

/**
 * This class is the controller for the new employee scene.
 * 
 */
public class NewEmployeeController implements Initializable {
	
    /** The username text field. */
    @FXML
    private TextField usernameTextField;

    /** The password text field. */
    @FXML
    private PasswordField passwordTextField;

    /** The confirm password text field. */
    @FXML
    private PasswordField confirmPasswordTextField;

    /** The Birthdate date picker. */
    @FXML
    private DatePicker Birthdate;


    /** The Name text field. */
    @FXML
    private TextField NameTextField;
        

	/** The Sex choice box. */
	@FXML
    private ChoiceBox<Gender> Sex;
    
   
    /** The Role choice box. */
    @FXML
    private ChoiceBox<EmployeePosition> Role;
    
    
    /** The Photo view. */
    @FXML
    private ImageView PhotoView;

    
    /** The error message. */
    @FXML
    private Label errorMsg;
       
    
    /** The image file. */
    private File imageFile;
    
    /** The image file changed boolean. */
    boolean imageFileChanged;
    
    
  
    
    
    /**
     * This method changes into admin page's scene.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void onBackClicked(MouseEvent event)  throws IOException{
    	SceneChanger.openWindow("views/AdminPage.fxml", new AdminPageController(), event);
    }
    
    
    /**
     * This Button saves the values for the new employee.
     * Has a condition that confirms the password fields, to see if they match.
     * When the employee is created with success the scene changes back to the admin page.`
     * 
     * @param event the event
     */
    @FXML
    void onSaveButtonPushed(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation");
    	alert.setHeaderText("Confirm");
    	alert.setContentText("Do you want to submit the information?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	
    		/*Condition where the password text field and password confirmation are compared*/
    	try {
    		if(!passwordTextField.getText().equals(confirmPasswordTextField.getText()))
    			{
    			errorMsg.setText("The passwords does not match");
    			}
    		/*Uploads the new employee's information*/
    		else {
    			Employee employee = new Employee(NameTextField.getText(),usernameTextField.getText(),passwordTextField.getText(),Sex.getValue(),Birthdate.getValue(),Role.getValue(),
    				imageFile);
    			EmployeeDAO.insertEmployeeIntoDB(employee, Sex.getValue(), Role.getValue());
    			errorMsg.setText("The employee was created successfully");
    			SceneChanger.openWindow("views/AdminPage.fxml", new AdminPageController(), event);
    			}
		} catch (Exception e) {
			
			errorMsg.setText(e.getMessage());
		}
    	
    	}
    }  
    
    /**
     * This method opens file chooser.
     *
     * @param event the event
     */
    @FXML
    void onUploadPhotoPushed(ActionEvent event) {
       
            //get the Stage to open a new window 
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
                        PhotoView.setImage(img);
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
	public void initialize(URL location, ResourceBundle resources) {
		
		//Show default image for Person
		 try {
	    	 imageFile = new File("src/pt/iade/dsm/images/DefaultPerson.png");
	         BufferedImage bufferedImage = ImageIO.read(imageFile);
	         Image image = SwingFXUtils.toFXImage(bufferedImage, null);
	         PhotoView.setImage(image);
	    	}
	    
	    catch(IOException e)
		 {
	        System.err.println(e.getMessage());
	    	
		 }
		
		 /*Inserts into choice boxes sex and role their values from database*/
		 Sex.setItems(Gender.getGenders());
		 
		 Role.setItems(EmployeePosition.getPositions());
		
		errorMsg.setText("");
		
	}

}
