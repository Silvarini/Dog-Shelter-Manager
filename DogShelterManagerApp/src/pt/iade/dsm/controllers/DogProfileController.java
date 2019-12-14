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




/**
 * This class is the controller for the dog's profile, 
 * where the information and the behavior of the interface get manipulated.
 */
public class DogProfileController implements Initializable {

	

    /** The dog's name label. */
    @FXML
    private Label nameLabel;

    /** The id label. */
    @FXML
    private Label idLabel;

    /** The info label. */
    @FXML
    private Label infoLabel;
    
    /** The lower info label. */
    @FXML
    private Label info2Label;
    
    /** The Observations text area. */
    @FXML
    private TextArea OBStextArea;

    /** The photo. */
    @FXML
    private ImageView photo;
    
    /** The dog. */
    private Dog dog;
    
   
    /**
     * On back clicked, returns to Dog Page.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void onBackClicked(MouseEvent event) throws IOException {
    	SceneChanger.openWindow("views/DogsPage.fxml", new DogsListController(), event);
    }
    
    
    /**
     * Adopt pushed, opens pop up for adoption request.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void AdoptPushed(ActionEvent event) throws IOException {
    PopUpDisplayer.showPopupWindow("views/AdoptionPopUp.fxml",new AdoptionPopUpController());
    	
    }

	/**
	 * This method sets values to all elements from the profile.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dog=DogsListController.getDog();
		
		nameLabel.setText("Hi I'm "+dog.getName());
		idLabel.setText("ID #"+String.valueOf(dog.getId()));
		
		infoLabel.setText("Breed: "+dog.getBreed()+"   |   Gender: "+dog.getGender()+"   |   Age: "+dog.getAge()+"   |   Size: "+dog.getSize());
		info2Label.setText("Coat Length: "+dog.getCoat()+"   |   Good With: "+dog.getGoodw());
		
		OBStextArea.setText(dog.getObs());
		
		/*Directory and setup for the dog's image*/
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
