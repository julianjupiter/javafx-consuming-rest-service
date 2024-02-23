package com.julianjupiter.sis.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ApiResponse {
    private final ApiStatus status;
    private final Object data;
    private final URI path;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private final OffsetDateTime createdAt;

    public ApiResponse(HttpStatus httpStatus, Object data, URI path, OffsetDateTime createdAt) {
        this.status = new ApiStatus(httpStatus.value(), httpStatus.getReasonPhrase());
        this.data = data;
        this.path = path;
        this.createdAt = createdAt;
    }

    public ApiStatus getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public URI getPath() {
        return path;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
