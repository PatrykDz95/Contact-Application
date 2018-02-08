package sample;

import javafx.beans.property.SimpleStringProperty;

public class ContactList {
    // using SimpleStringProperty to add listeners or bind it to other properties
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty notes;

    public ContactList(String firstName, String lastName, String phoneNumber, String notes) {

        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.notes = new SimpleStringProperty(notes);
    }

    public String getFirstName() {
        return firstName.get();
    }


    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String firstName) {
        this.lastName.set(firstName);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String firstName) {
        this.phoneNumber.set(firstName);
    }

    public String getNotes() {
        return notes.get();
    }

    public void setNotes(String firstName) {
        this.notes.set(firstName);
    }
}
