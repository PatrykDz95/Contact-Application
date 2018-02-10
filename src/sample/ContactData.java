package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class ContactData {

    private static ContactData instance = new ContactData();
    private TableView<ContactList> table;
    private ObservableList<ContactList> data;

    public static ContactData getInstance(){
        return instance;
    }

    private ContactData(){
        table = new TableView<>();
        data = FXCollections.observableArrayList();
    }

    public ObservableList<ContactList> getData() {
        return data;
    }

    public void deletePerson(ContactList person){
        data.remove(person);
    }

    public TableView<ContactList> getTable() {
        return table;
    }

}