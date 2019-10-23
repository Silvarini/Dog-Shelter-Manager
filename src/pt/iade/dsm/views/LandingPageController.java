package pt.iade.dsm.views;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LandingPageController{

	//This are for the  Log In.
	  @FXML private TextField usernameTextField;
	  @FXML private PasswordField passwordField;
	  @FXML private Button logInButton;
	  
	  //This is for the Icon in the center,
	  @FXML private ImageView iconImageView;
	  
	  //This are the navigation buttons.
	  @FXML private Button animalsButton;
	  @FXML private Button volunteersButton;
	  @FXML private Button donationButton;
	  
	  
	  
	  //CANT GIVE ID TO STUFF...
	  public void logInButton(ActionEvent event) {
			System.out.println("Logged In");

	    }	

	  //This changes scene into the AnimalsPage.
	  
	   public void animalsButton(ActionEvent event) throws IOException {
			
		  Parent animalsPageParent = FXMLLoader.load(getClass().getResource("AnimalsPage.fxml"));
		  Scene animalsPageScene = new Scene(animalsPageParent);
		  
		  //This line gets the Stage information.
		  Stage scene = (Stage)((Node)event.getSource()).getScene().getWindow();
		  
		  scene.setScene(animalsPageScene);
		  scene.show();
		  

	    }
	  
	  

}
