package com.julianjupiter.sis.viewmodel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julianjupiter.sis.client.StudentClient;
import com.julianjupiter.sis.client.StudentClientImpl;
import com.julianjupiter.sis.client.StudentResponse;
import com.julianjupiter.sis.viewmodel.property.StudentProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.http.HttpResponse;

public class StudentListViewModel {
    private static final System.Logger LOGGER = System.getLogger(StudentListViewModel.class.getName());
    private final ObjectMapper objectMapper;
    private final StudentClient studentClient;
    private final ObjectProperty<ObservableList<StudentProperty>> masterStudentPropertyObservableList = new SimpleObjectProperty<>(FXCollections.observableArrayList());

    public StudentListViewModel(String sisServiceBaseUrl) {
        this.objectMapper = new ObjectMapper();
        this.studentClient = new StudentClientImpl(sisServiceBaseUrl);
    }

    public ObservableList<StudentProperty> getMasterStudentPropertyObservableList() {
        return masterStudentPropertyObservableList.get();
    }

    public ObjectProperty<ObservableList<StudentProperty>> masterStudentPropertyObservableListProperty() {
        return masterStudentPropertyObservableList;
    }

    public void findAll() throws IOException, InterruptedException {
        HttpResponse<String> response = this.studentClient.findAll();
        if (response.statusCode() == 200) {
            String body = response.body();
            if (body != null) {
                LOGGER.log(System.Logger.Level.INFO, body);
                var studentResponse = this.objectMapper.readValue(body, StudentResponse.class);
                var studentProperties = studentResponse.data().stream()
                        .map(student -> new StudentProperty()
                                .setId(student.id())
                                .setLastName(student.lastName())
                                .setFirstName(student.firstName())
                                .setMiddleName(student.middleName())
                        )
                        .toList();
                this.masterStudentPropertyObservableList.get()
                        .setAll(studentProperties);
            }
        }


    }
}
