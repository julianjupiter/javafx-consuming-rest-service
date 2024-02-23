package com.julianjupiter.sis.client;

import com.julianjupiter.sis.model.Student;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class StudentClientImpl extends BaseClient implements StudentClient {
    private final String sisServiceBaseUrl;

    public StudentClientImpl(String sisServiceBaseUrl) {
        this.sisServiceBaseUrl = sisServiceBaseUrl;
    }

    @Override
    public HttpResponse<String> findAll() throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(this.sisServiceBaseUrl + "/v1/students"))
                .build();

        return super.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Override
    public HttpResponse<String> findById(Long id) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(this.sisServiceBaseUrl + "/students/" + id))
                .build();
        return super.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Override
    public HttpResponse<String> create(Student student) {
        return null;
    }

    @Override
    public HttpResponse<String> update(Long id) {
        return null;
    }

    @Override
    public HttpResponse<Void> delete(Long id) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(this.sisServiceBaseUrl + "/students/" + id))
                .build();

        return super.httpClient.send(request, HttpResponse.BodyHandlers.discarding());
    }
}
