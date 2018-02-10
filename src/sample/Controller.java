package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;

public class Controller {

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

    private static String filename = "ContactList.txt";
    public Boolean activeLoadButton = true;

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
    public void loadPersonInfo() throws IOException {
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);
        String input;

        try{
            while ((input = br.readLine())!=null && activeLoadButton){
                String[] itemPieces = input.split("\t");
                // loading persons from txt file
                String firstColumne = itemPieces[0];
                String lastColumne = itemPieces[1];
                String phoneColumne = itemPieces[2];
                String notesColumne = itemPieces[3];

                ContactList todoItem = new ContactList(firstColumne,lastColumne,phoneColumne,notesColumne);

                ContactData.getInstance().getData().add(todoItem);

                }
            activeLoadButton = false;

        }finally {
            if (br != null) {
                br.close();
            }
        }
    }
    @FXML
    public void savePersonInfo() throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try{
            //saving perons to txt file
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
            if(bw!=null) {
                bw.close();
            }
    }
    }

    @FXML
    public void addNewRow() {

        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty fields!");
            alert.setHeaderText("The Name or Last Name fields are empty!");
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
    public void deletePersonRow() {
        try {
            ContactList person = ContactData.getInstance().getTable().getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("Want to delete " + ContactData.getInstance().getTable().
                    getSelectionModel().getSelectedItem().getFirstName() + "?");
            alert.setContentText("Are you sure? Click OK to confirm, or cancel to Back out.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                ContactData.getInstance().deletePerson(person);
            }
        }catch (Exception e){
                Alert NotChosenPerson = new Alert(Alert.AlertType.ERROR);
                NotChosenPerson.setHeaderText("You must first select a person to delete");
                Optional<ButtonType> smallAlert = NotChosenPerson.showAndWait();


            }

    }



}