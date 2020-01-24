package pt.iade.dsm.controllers;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.iade.dsm.Main;

/**
 * Class for scene changing.
 */
public class SceneChanger {

	/**
	 * Open window.
	 *
	 * @param viewPath the view path
	 * @param controller the controller
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void openWindow(String viewPath, 
			Object controller, Event event) throws IOException {
			FXMLLoader loader = new FXMLLoader(
					Main.class.getResource(viewPath));
			loader.setController(controller);;
			Parent root = loader.load();
			Scene scene = new Scene(root);
			

				Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
				
		        window.setScene(scene);
		        window.show();
		    

}
	

	
	}


		