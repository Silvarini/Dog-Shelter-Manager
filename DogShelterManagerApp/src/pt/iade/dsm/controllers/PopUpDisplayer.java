package pt.iade.dsm.controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.iade.dsm.Main;

// TODO: Auto-generated Javadoc
/**
 * The Class PopUpDisplayer.
 */
public class PopUpDisplayer {
	
	
	 /**
 	 * Show popup window.
 	 *
 	 * @param view the view
 	 * @param controller the controller
 	 */
 	public static void showPopupWindow(String view, Object controller) {

	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource(view));
	        
	        // initializing the controller
	        loader.setController(controller);
	        Parent root;
	        try {
	            root = loader.load();
	            Scene scene = new Scene(root);
	            // this is the popup stage
	            Stage popupStage = new Stage();
	            
	            popupStage.initModality(Modality.APPLICATION_MODAL);
	            popupStage.setScene(scene);
	            popupStage.showAndWait();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	    }

}
