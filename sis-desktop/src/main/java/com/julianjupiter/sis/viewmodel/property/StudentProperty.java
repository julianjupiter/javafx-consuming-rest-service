package com.julianjupiter.sis.viewmodel.property;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentProperty {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty middleName = new SimpleStringProperty();



    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public StudentProperty setId(long id) {
        this.id.set(id);
        return this;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StudentProperty setLastName(String lastName) {
        this.lastName.set(lastName);
        return this;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StudentProperty setFirstName(String firstName) {
        this.firstName.set(firstName);
        return this;
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public StringProperty middleNameProperty() {
        return middleName;
    }

    public StudentProperty setMiddleName(String middleName) {
        this.middleName.set(middleName);
        return this;
    }
}
