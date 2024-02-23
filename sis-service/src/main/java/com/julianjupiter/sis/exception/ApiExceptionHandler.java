package com.julianjupiter.sis.exception;

import com.julianjupiter.sis.controller.response.ErrorMessage;
import com.julianjupiter.sis.controller.response.ErrorResponse;
import com.julianjupiter.sis.util.AppUtils;
import com.julianjupiter.sis.util.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundException(NotFoundException exception) {
        return new ErrorResponse(
                HttpStatus.NOT_FOUND,
                exception.getErrorMessages(),
                AppUtils.createUri(),
                OffsetDateTime.now()
        );
    }

    @ExceptionHandler(BeanValidationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse beanValidationException(BeanValidationException exception) {
        return new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY,
                exception.getErrorMessages(),
                AppUtils.createUri(),
                OffsetDateTime.now()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse exception(Exception exception) {
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                List.of(new ErrorMessage(ErrorCode.ERR500, exception.getMessage())),
                AppUtils.createUri(),
                OffsetDateTime.now()
        );
    }
}
