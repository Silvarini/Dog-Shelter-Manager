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
import pt.iade.dsm.models.Dog;
import pt.iade.dsm.models.State;

public class NewDogController implements Initializable{

    @FXML
    private TextField DogName_tf;

    @FXML
    private ChoiceBox<String> dogBreed;

    @FXML
    private ChoiceBox<String> dogGender;

    @FXML
    private ChoiceBox<String> dogSize;

    @FXML
    private ChoiceBox<String> dogCL;

    @FXML
    private ChoiceBox<String> dogGw;

    @FXML
    private ChoiceBox<String> dogAge;
    
    @FXML
    private TextField obsField;
    
    @FXML
    private Label saveInfo;
    
    @FXML
    private Label backPage;
    
    @FXML
    private ImageView imageView;
    
    private File imageFile;
    
    boolean imageFileChanged;
        
    
    @FXML
    void onBackClicked(MouseEvent event) throws IOException {
    	SceneChanger.openWindow("views/EmployeePage.fxml", new EmployeePageController(), event);
    }
  
      
    @FXML
    void SaveData(ActionEvent event)  {
    	    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation");
    	alert.setHeaderText("Confirm");
    	alert.setContentText("Do you want to submit the information?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	try 
    	{
    	Dog dog = new Dog(DogName_tf.getText(),dogBreed.getValue().toString(),dogAge.getValue().toString(),dogGender.getValue().toString(),
    					  dogSize.getValue().toString(),dogCL.getValue().toString(),dogGw.getValue().toString(),obsField.getText(),imageFile);
		DogDAO.insertDogIntoDB(dog);
		
		for (Dog newDog: DogDAO.loadDogs()) {
			if(newDog.getName().equals(DogName_tf.getText())) {
			State state = new State(newDog.getState(), newDog.getId(), LoginInController.getEmployee().getEmployeeID());	
			HistoricDAO.insertStateDB(state);		
			}
		}
		SceneChanger.openWindow("views/EmployeePage.fxml", new EmployeePageController(), event);
		}
    	catch (Exception e){
    		saveInfo.setText(e.getMessage());	
    	}
    	}
    }

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
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) { 	
    	
    	
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
    	
    	String gw[] = {"Dogs","Kids","All Species","None","Birds","Cats"};
    	dogGw.getItems().addAll(gw);
		
    	String age[] = {"Puppy","Young","Adult","Senior"};
		dogAge.getItems().addAll(age);
		
		String breed[] = {"Beagle","Boerboel","Anatolian Sheperd","Bullboxer Pit","Cane Corso","Dachsador","Labradane","Corgi"};
		dogBreed.getItems().addAll(breed);
		
		String size[] = {"XS","small","medium","large","XL"};
		dogSize.getItems().addAll(size);
		
		dogGender.getItems().add("M");
		dogGender.getItems().add("F");
		
		dogCL.getItems().add("small");
		dogCL.getItems().add("medium");
		dogCL.getItems().add("large");
		
		
	}


}
