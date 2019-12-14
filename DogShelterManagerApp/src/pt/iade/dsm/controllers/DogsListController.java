package pt.iade.dsm.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

public class DogsListController implements Initializable{

	@FXML
	private TableView<Dog> tableDog;

	@FXML
	private TableColumn<Dog, String> nameColumn;

	@FXML
	private TableColumn<Dog, String> breedColumn;

	@FXML
	private TableColumn<Dog, String> genderColumn;

	@FXML
	private TableColumn<Dog, String> ageColumn;
	
	@FXML
    private TextField filter;
	
	//private ObservableList<Dog> masterData = FXCollections.observableArrayList();
    
	private static Dog dog;


	@FXML
	void dogSelected(MouseEvent event) throws IOException {
		 setDog(tableDog.getSelectionModel().getSelectedItem());
		 SceneChanger.openWindow("views/DogProfile.fxml", new DogProfileController(), event);
	}
		

	@FXML
    void onLogoClicked(MouseEvent event) throws IOException {
		SceneChanger.openWindow("views/LandingPage.fxml",new LandingPageController(), event);
    }
	
/*	
	public void DogTable() {
		try {
			masterData.addAll(DogDAO.loadDogs());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
*/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("name"));
		breedColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("breed"));
		genderColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("gender"));
		ageColumn.setCellValueFactory(new PropertyValueFactory<Dog, String>("age"));
		
		
		try {	
			for (Dog dog: DogDAO.loadDogs())
			{
				if(dog.getState().equals("not adopted"))
				tableDog.getItems().add(dog);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	/*	FilteredList<Dog> filteredData = new FilteredList<>(masterData, p -> true);
		
		
		filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(dog -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (dog.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (dog.getBreed().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
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

		*/
	}


	public static Dog getDog() {
		return dog;
	}


	public static void setDog(Dog dog) {
		DogsListController.dog = dog;
	}



}
