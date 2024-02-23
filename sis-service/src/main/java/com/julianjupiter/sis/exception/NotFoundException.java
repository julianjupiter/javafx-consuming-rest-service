package com.julianjupiter.sis.exception;

import com.julianjupiter.sis.controller.response.ErrorMessage;

import java.util.List;

public class NotFoundException extends RuntimeException {
    private final List<ErrorMessage> errorMessages;

    public NotFoundException(String message, List<ErrorMessage> errorMessages) {
        super(message);
        this.errorMessages = errorMessages;
    }

    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }
}
