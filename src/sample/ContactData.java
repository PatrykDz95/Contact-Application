package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class ContactData {

    private static ContactData instance = new ContactData();
    private static String filename = "TodoListItems.txt";
    private TableView<ContactList> table;
    private ObservableList<ContactList> data;

    public static ContactData getInstance(){
        return instance;
    }

    private ContactData(){
        table = new TableView<>();
        data = FXCollections.observableArrayList();
    }

    // Using ObservableList for performance reasons
    public ObservableList<ContactList> getData() {
        return data;
    }

    public void addTodoItem(ContactList person){
        data.add(person);
    }



    public void deleteTodoItem(ContactList person){
        data.remove(person);
    }

    public TableView<ContactList> getTable() {
        return table;
    }
}