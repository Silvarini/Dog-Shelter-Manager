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

public class NewEmployeeController implements Initializable {
	
    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private DatePicker Birthdate;


    @FXML
    private TextField NameTextField;
        

	@FXML
    private ChoiceBox<String> Sex;
    
   
    @FXML
    private ChoiceBox<String> Role;
    
    
    @FXML
    private ImageView PhotoView;

    
    @FXML
    private Label errorMsg;
       
    
    private File imageFile;
    boolean imageFileChanged;
    
    
  
    
    
    @FXML
    void onBackClicked(MouseEvent event)  throws IOException{
    	SceneChanger.openWindow("views/AdminPage.fxml", new AdminPageController(), event);
    }
    
    
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
		Role.getItems().add("Funcionário");
		
		errorMsg.setText("");
		
	}

}
