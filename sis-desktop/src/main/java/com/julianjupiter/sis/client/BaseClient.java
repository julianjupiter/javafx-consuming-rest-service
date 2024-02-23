package com.julianjupiter.sis.client;

import java.net.http.HttpClient;
import java.time.Duration;

public abstract class BaseClient {
    protected final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
}
