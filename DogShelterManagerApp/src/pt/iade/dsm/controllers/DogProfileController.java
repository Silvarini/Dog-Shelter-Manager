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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pt.iade.dsm.models.Dog;




public class DogProfileController implements Initializable {

	

    @FXML
    private Label nameLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label infoLabel;
    
    @FXML
    private Label info2Label;
    
    @FXML
    private TextArea OBStextArea;

    @FXML
    private ImageView photo;
    
    private Dog dog;
    
   
    @FXML
    void onBackClicked(MouseEvent event) throws IOException {
    	SceneChanger.openWindow("views/DogsPage.fxml", new DogsListController(), event);
    }
    
    
    @FXML
    void AdoptPushed(ActionEvent event) throws IOException {
    PopUpDisplayer.showPopupWindow("views/AdoptionPopUp.fxml",new AdoptionPopUpController());
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dog=DogsListController.getDog();
		
		nameLabel.setText("Hi I'm "+dog.getName());
		idLabel.setText("ID #"+String.valueOf(dog.getId()));
		
		infoLabel.setText("Breed: "+dog.getBreed()+"   |   Gender: "+dog.getGender()+"   |   Age: "+dog.getAge()+"   |   Size: "+dog.getSize());
		info2Label.setText("Coat Length: "+dog.getCoat()+"   |   Good With: "+dog.getGoodw());
		
		OBStextArea.setText(dog.getObs());
		
		 try{
	            String imgLocation = "src/pt/iade/dsm/images/Dogs/" + DogsListController.getDog().getPhoto().getName();
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

}
