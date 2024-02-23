package com.julianjupiter.sis.controller;

import com.julianjupiter.sis.Config;
import com.julianjupiter.sis.core.Controller;
import com.julianjupiter.sis.core.View;
import com.julianjupiter.sis.viewmodel.StudentListViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class MainController implements Controller, Initializable {
    private final Config config;
    private Stage primaryStage;
    private ResourceBundle resourceBundle;
    @FXML
    private BorderPane mainBorderPane;

    public MainController(Config config, Stage primaryStage) {
        this.config = config;
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.initStudentList();
    }

    private void initStudentList() {
        var studentListViewModel = new StudentListViewModel(this.config.sisServiceBaseUrl());
        Map<Class<StudentListController>, Callable<?>> studentListControllerFactory = Map.of(
                StudentListController.class,
                () -> new StudentListController(studentListViewModel)
        );
        View<StudentListController, BorderPane> view = new View<>(StudentListController.class, this.resourceBundle, studentListControllerFactory);
        BorderPane borderPane = view.component();
        this.mainBorderPane.setCenter(borderPane);
    }
}
