package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Optional;

public class Controller {


  //  TableView<ContactList> table;
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

   // private ObservableList<ContactList> data;

    private static String filename = "ContactList.txt";

    public void initialize() {





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

        ContactData.getInstance().getTable().setItems( ContactData.getInstance().getData());
        ContactData.getInstance().getTable().getColumns().addAll(firstColumn, lastNameColumn, phoneColumn, notesColumn);
        gridpane.getChildren().add(ContactData.getInstance().getTable());
    }



    @FXML
    public void loadTodoItems() throws IOException {
       // data = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);
        String input;

        try{
            while ((input = br.readLine())!=null){
                String[] itemPieces = input.split("\t");

                String firstColumne = itemPieces[0];
                String lastColumne = itemPieces[1];
                String phoneColumne = itemPieces[2];
                String notesColumne = itemPieces[3];

               // LocalDate date = LocalDate.parse(dateString,formatter);
                ContactList todoItem = new ContactList(firstColumne,lastColumne,phoneColumne,notesColumne);
//                if(!(data.contains(todoItem))){  TODO: add if to not load the same note for ever
                ContactData.getInstance().getData().add(todoItem);
//                }

            }
        }finally {

        }
    }
    @FXML
    public void storeTodoItems() throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try{
            Iterator<ContactList> iter = ContactData.getInstance().getData().iterator();
            while(iter.hasNext()){
                ContactList item = iter.next();
                bw.write(String.format("%s\t%s\t%s\t%s",
                        item.getFirstName(),
                        item.getLastName(),
                        item.getPhoneNumber(),
                        item.getNotes()));
                bw.newLine();

            }
        }finally {
            if(bw!=null){
                bw.close();
            }
        }
    }





    @FXML
    public void addPerson() {
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty fields!");
            alert.setHeaderText("The Name and Last Name fields are empty!");
            alert.setContentText("Please fill the required fields");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            ContactData.getInstance().getData().add(new ContactList(firstNameField.getText(), lastNameField.getText(),
                    phoneNumberField.getText(), notesField.getText()));

            firstNameField.clear();
            lastNameField.clear();
            phoneNumberField.clear();
            notesField.clear();
        }
    }

    @FXML
    public void deleteItem() {
        ContactList item = ContactData.getInstance().getTable().getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Want to delete " + ContactData.getInstance().getTable().getSelectionModel().getSelectedItem().getFirstName() + "?");
        alert.setContentText("Are you sure? Click OK to confirm, or cancel to Back out.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            deleteTodoItem(item);

        }

    }

    public void deleteTodoItem(ContactList item) {
        ContactData.getInstance().getData().remove(item);
    }

}