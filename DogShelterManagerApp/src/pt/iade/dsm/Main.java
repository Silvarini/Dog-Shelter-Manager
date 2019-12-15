package pt.iade.dsm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.iade.dsm.controllers.LandingPageController;
/*
 * Dog Shelter Manager
 * @author N'zembo 50039011 & Bernardo 50039481
 * 
 * 
 * This is the main class.
 * */
public class Main extends Application {
	
	 public static void main(String[] args)
	    {
	        launch(args);
	    }


    public void start(Stage primaryStage) throws Exception {
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/LandingPage.fxml"));
		loader.setController(new LandingPageController());
    	Parent root = loader.load();
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("DSM APP");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}
