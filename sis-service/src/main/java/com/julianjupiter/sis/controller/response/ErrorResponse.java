package com.julianjupiter.sis.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;

public final class ErrorResponse {
    private final ApiStatus status;
    @JsonProperty("errors")
    private final List<ErrorMessage> errorMessages;
    private final URI path;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private final OffsetDateTime createdAt;

    public ErrorResponse(HttpStatus status, List<ErrorMessage> errorMessages, URI path, OffsetDateTime createdAt) {
        this.status = new ApiStatus(status.value(), status.getReasonPhrase());
        this.errorMessages = errorMessages;
        this.path = path;
        this.createdAt = createdAt;
    }

    public ApiStatus getStatus() {
        return status;
    }

    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }

    public URI getPath() {
        return path;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
