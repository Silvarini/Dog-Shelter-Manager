package pt.iade.dsm.controllers;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.iade.dsm.DAO.EmployeeDAO;
import pt.iade.dsm.models.Employee;

// TODO: Auto-generated Javadoc
/**
 * The Class NewEmployeeController.
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

    /** The Birthdate. */
    @FXML
    private DatePicker Birthdate;


    /** The Name text field. */
    @FXML
    private TextField NameTextField;
        

	/** The Sex. */
	@FXML
    private ChoiceBox<String> Sex;
    
   
    /** The Role. */
    @FXML
    private ChoiceBox<String> Role;
    
    
    /** The Photo view. */
    @FXML
    private ImageView PhotoView;

    
    /** The error msg. */
    @FXML
    private Label errorMsg;
       
    
    /** The image file. */
    private File imageFile;
    
    /** The image file changed. */
    boolean imageFileChanged;
    
    
  
    
    
    /**
     * On back clicked.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void onBackClicked(MouseEvent event)  throws IOException{
    	SceneChanger.openWindow("views/AdminPage.fxml", new AdminPageController(), event);
    }
    
    
    /**
     * On save button pushed.
     *
     * @param event the event
     */
    @FXML
    void onSaveButtonPushed(ActionEvent event) {
    	try {
    		if(!passwordTextField.getText().equals(confirmPasswordTextField.getText()))
    			{
    			errorMsg.setText("The passwords does not match");
    			}
    		else {
    			Employee employee = new Employee(NameTextField.getText(),usernameTextField.getText(),passwordTextField.getText(),Sex.getValue().toString(),Birthdate.getValue(),Role.getValue().toString(),
    				imageFile);
    			EmployeeDAO.insertEmployeeIntoDB(employee);
    			errorMsg.setText("The employee was created successfully");
    			Thread.sleep(3000);
    			SceneChanger.openWindow("views/AdminPage.fxml", new AdminPageController(), event);
    			}
		} catch (Exception e) {
			
			errorMsg.setText(e.getMessage());
		}
    	
    	
    }  
    
    /**
     * On upload photo pushed.
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
		
		
		Sex.getItems().add("M");
		Sex.getItems().add("F");
		
		Role.getItems().add("Administrador");
		Role.getItems().add("Funcion�rio");
		
		errorMsg.setText("");
		
	}

}
