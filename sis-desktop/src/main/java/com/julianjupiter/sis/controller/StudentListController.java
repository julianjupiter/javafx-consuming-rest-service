package com.julianjupiter.sis.controller;

import com.julianjupiter.sis.core.Controller;
import com.julianjupiter.sis.viewmodel.StudentListViewModel;
import com.julianjupiter.sis.viewmodel.property.StudentProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class StudentListController implements Controller, Initializable {
    private final StudentListViewModel studentListViewModel;
    private ResourceBundle resourceBundle;
    @FXML
    private TableView<StudentProperty> studentPropertyTableView;
    @FXML
    private TableColumn<StudentProperty, Long> idTableColumn;
    @FXML
    private TableColumn<StudentProperty, String> lastNameTableColumn;
    @FXML
    private TableColumn<StudentProperty, String> firstNameTableColumn;
    @FXML
    private TableColumn<StudentProperty, String> middleNameTableColumn;

    public StudentListController(StudentListViewModel studentListViewModel) {
        this.studentListViewModel = studentListViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        this.bindViewModel();
        this.initStudentPropertyTableView();
        try {
            this.studentListViewModel.findAll();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void bindViewModel() {
        this.studentPropertyTableView.itemsProperty().bind(this.studentListViewModel.masterStudentPropertyObservableListProperty());
    }

    private void initStudentPropertyTableView() {
        this.idTableColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        this.lastNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        this.firstNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        this.middleNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().middleNameProperty());
    }
}
