package com.julianjupiter.sis.client;

import com.julianjupiter.sis.model.Student;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

public interface StudentClient {
    HttpResponse<String> findAll() throws IOException, InterruptedException;

    HttpResponse<String> findById(Long id) throws IOException, InterruptedException;

    HttpResponse<String> create(Student student);

    HttpResponse<String> update(Long id);

    HttpResponse<Void> delete(Long id) throws IOException, InterruptedException;
}
