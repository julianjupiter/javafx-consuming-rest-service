package com.julianjupiter.sis.exception;

import com.julianjupiter.sis.controller.response.ErrorMessage;

import java.util.List;

public class BeanValidationException extends RuntimeException {
    private final List<ErrorMessage> errorMessages;

    public BeanValidationException(String message, List<ErrorMessage> errorMessages) {
        super(message);
        this.errorMessages = errorMessages;
    }

    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }
}
