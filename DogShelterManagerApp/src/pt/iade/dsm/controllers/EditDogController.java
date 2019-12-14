package pt.iade.dsm.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;




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
    void onSubmitPushed(ActionEvent event) {

    }

    @FXML
    void onUploadPushed(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

	
}
