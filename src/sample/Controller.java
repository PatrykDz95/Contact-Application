package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.Optional;

public class Controller {


    TableView<ContactList> table;
//    @FXML
//    TableView tableView;
    @FXML
    private TableColumn firstColumn;
    @FXML
    private TableColumn lastNameColumn;
    @FXML
    private TableColumn phoneColumn;
    @FXML
    private TableColumn notesColumn;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField notesField;
    @FXML
    private GridPane gridpane;

    private ObservableList<ContactList> data;

    public void initialize() {
                table = new TableView<>();
                data = FXCollections.observableArrayList(
                new ContactList("Jacob", "Smith", "jacob.smith@example.com", "note"),
                new ContactList("Isabella", "Johnson", "isabella.johnson@example.com", "note"),
                new ContactList("Ethan", "Williams", "ethan.williams@example.com", "note"),
                new ContactList("Emma", "Jones", "emma.jones@example.com", "note"),
                new ContactList("Michael", "Brown", "michael.brown@example.com", "note")
        );


        firstColumn.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );
        lastNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );
        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phoneNumber")
        );

        notesColumn.setCellValueFactory(
                new PropertyValueFactory<>("notes")
        );

        table.setItems(data);
        table.getColumns().addAll(firstColumn, lastNameColumn, phoneColumn, notesColumn);
        gridpane.getChildren().add(table);
    }

    @FXML
    public void addPerson(ActionEvent event){
        if(firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty fields!");
            alert.setHeaderText("The Name and Last Name fields are empty!");
            alert.setContentText("Please fill the required fields");
            Optional<ButtonType> result = alert.showAndWait();
        }else {
            data.add(new ContactList(firstNameField.getText(), lastNameField.getText(),
                    phoneNumberField.getText(), notesField.getText()));

            firstNameField.clear();
            lastNameField.clear();
            phoneNumberField.clear();
            notesField.clear();
        }
    }

    @FXML
    public void deleteItem(){
        ContactList item =  table.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Want to delete " + table.getSelectionModel().getSelectedItem().getFirstName() + "?");
        alert.setContentText("Are you sure? Click OK to confirm, or cancel to Back out.");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && (result.get() == ButtonType.OK)){
           deleteTodoItem(item);

        }

            }

    public void deleteTodoItem(ContactList item){
        data.remove(item);
    }
}