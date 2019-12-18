package pt.iade.dsm.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
/*import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;*/
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pt.iade.dsm.models.Dog;
import pt.iade.dsm.DAO.DogDAO;

/**
 * This class is the controller for the dog list page 
 * where the dogs that are available for adoption are showed. 
 * */
public class DogsListController implements Initializable{

	/** The table dog. */
	@FXML
	private TableView<Dog> tableDog;

	/** The name column. */
	@FXML
	private TableColumn<Dog, String> nameColumn;

	/** The breed column. */
	@FXML
	private TableColumn<Dog, String> breedColumn;

	/** The gender column. */
	@FXML
	private TableColumn<Dog, String> genderColumn;

	/** The age column. */
	@FXML
	private TableColumn<Dog, String> ageColumn;
	
	/** The filter of results.
	 * Still in development.
	 */
	@FXML
    private TextField filter;
	    
	/** The dog. */
	private static Dog dog;


	/**
	 * This method reads the user's selection from table to open the dog's profile,.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void dogSelected(MouseEvent event) throws IOException {
		 setDog(tableDog.getSelectionModel().getSelectedItem());
		 SceneChanger.openWindow("views/DogProfile.fxml", new DogProfileController(), event);
	}
		

	/**
	 * This method returns the page into the Landing page, when the logo gets clicked.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
    void onLogoClicked(MouseEvent event) throws IOException {
		SceneChanger.openWindow("views/LandingPage.fxml",new LandingPageController(), event);
    }
	
	
	/**
	 * This method sets all values to the columns of the table.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("name"));
		breedColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("breed"));
		genderColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("gender"));
		ageColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("age"));
		
		
		try {	
				tableDog.getItems().addAll(DogDAO.loadAdoptableDogs());				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		FilteredList<Dog> filteredData;
		try {
			filteredData = new FilteredList<>( DogDAO.loadAdoptableDogs(), p -> true);
		

        // 2. Set the filter Predicate whenever the filter changes.
        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(dog -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(dog.getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches name.
                } else if (String.valueOf(dog.getBreed()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches breed.
                } else if (String.valueOf(dog.getGender()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches gender.
                } 
                

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Dog> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableDog.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        tableDog.setItems(sortedData);
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		}


	/**
	 * Gets the dog.
	 *
	 * @return the dog
	 */
	public static Dog getDog() {
		return dog;
	}


	/**
	 * Sets the dog.
	 *
	 * @param dog the new dog
	 */
	public static void setDog(Dog dog) {
		DogsListController.dog = dog;
	}



}
