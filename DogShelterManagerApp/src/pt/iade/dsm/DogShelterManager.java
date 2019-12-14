package pt.iade.dsm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.iade.dsm.controllers.LandingPageController;


public class DogShelterManager extends Application {

	    
	public static void main (String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/LandingPage.fxml"));
		loader.setController(new LandingPageController());
		Parent root = loader.load(); 
				//FXMLLoader.load(getClass().getResource("../Views/LandingPage.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Dog Shelter Manager");
        stage.setScene(scene);
        stage.show();
		
		
	}

	
	
}

