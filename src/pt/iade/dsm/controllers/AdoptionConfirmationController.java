package pt.iade.dsm.controllers;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

public class AdoptionConfirmationController {
	ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList(
		    "First", "Second", "Third")
		);
}
	