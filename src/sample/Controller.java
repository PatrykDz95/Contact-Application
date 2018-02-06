package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class Controller {

    @FXML
    TableView<ContactList> table;
    @FXML
    private TableColumn firstColumn;
    @FXML
    private TableColumn lastNameColumn;
    @FXML
    private TableColumn phoneColumn;
    @FXML
    private TableColumn notesColumn;
    @FXML
    private GridPane cos;
    public void initialize() {
                 table = new TableView<>();
                ObservableList<ContactList> data = FXCollections.observableArrayList(
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

        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<ContactList, String>("notes")
        );

        table.setItems(data);
        table.getColumns().addAll(firstColumn, lastNameColumn, phoneColumn, notesColumn);
        cos.getChildren().add(table);
    }


//    @FXML
//    public void showContactsList() {
//
//}
}