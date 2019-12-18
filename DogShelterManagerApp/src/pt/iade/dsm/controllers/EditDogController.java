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
import pt.iade.dsm.models.Dog;
import pt.iade.dsm.models.State;




/*
 * This class is still in development...
 * 
 * */

public class EditDogController implements Initializable {
	
	@FXML
    private ImageView photoIV;

    @FXML
    private ChoiceBox<String> sizeCB;

    @FXML
    private ChoiceBox<String> coatCB;

    @FXML
    private ChoiceBox<String> stateCB;

    @FXML
    private ChoiceBox<String> ageCB;

    @FXML
    private TextArea obsText;

    @FXML
    private Label nameL;

    @FXML
    private Label idL;

    @FXML
    private Label genderL;
    
    @FXML
    private Label eMSG;
    
    private File imageFile;
    
    boolean imageFileChanged;
    
    private Dog eDog;
    

    @FXML
    void onSubmitPushed(ActionEvent event) throws IOException {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation");
    	alert.setHeaderText("Confirm");
    	alert.setContentText("Do you want to submit the information?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		eDog.setAge(ageCB.getValue());
    		eDog.setCoat(coatCB.getValue());
    		eDog.setObs(obsText.getText());
    		eDog.setPhoto(imageFile);
    		eDog.setSize(sizeCB.getValue());
    		
    		if(eDog.getState()!=stateCB.getValue()) {
    			eDog.setState(stateCB.getValue());
    			State newState = new State(eDog.getState(), eDog.getId(), LoginInController.getEmployee().getEmployeeID());
    			try {
					HistoricDAO.insertStateDB(newState);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}
    		
    		try {
				DogDAO.updateDog(eDog);
			} catch (SQLException e) {
				eMSG.setText(e.getMessage());
			}	
    	}
    	SceneChanger.openWindow("views/EmployeePage.fxml", new EmployeePageController(), event);	
    }

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		eDog=EmployeePageController.getDog();
		
		nameL.setText(eDog.getName());
		genderL.setText(eDog.getGender());
		idL.setText(String.valueOf(eDog.getId()));
		
		obsText.setPromptText(eDog.getObs());
		
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
		
		String age[] = {"Puppy","Young","Adult","Senior"};
			ageCB.getItems().addAll(age);
			ageCB.setValue(eDog.getAge());
		String size[] = {"XS","small","medium","large","XL"};
			sizeCB.getItems().addAll(size);
			sizeCB.setValue(eDog.getSize());
			
			coatCB.getItems().add("small");
			coatCB.getItems().add("medium");
			coatCB.getItems().add("large");
			coatCB.setValue(eDog.getCoat());
			
			stateCB.getItems().add("dead");
			stateCB.getItems().add("returned");
			stateCB.setValue(eDog.getState());
	}

	
}
