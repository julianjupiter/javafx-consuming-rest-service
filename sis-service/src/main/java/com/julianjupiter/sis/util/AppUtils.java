package com.julianjupiter.sis.util;

import com.julianjupiter.sis.controller.response.ErrorMessage;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

public class AppUtils {
    private AppUtils() {
    }

    public static URI createUri() {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .buildAndExpand()
                .toUri();
    }

    public static URI createUri(String path, Object... uriVariableValues) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path(path)
                .buildAndExpand(uriVariableValues)
                .toUri();
    }

    public static BindingResult validate(Validator validator, Object target, String objectName) {
        var dataBinder = new DataBinder(target, objectName);
        dataBinder.addValidators(validator);
        dataBinder.validate();

        return dataBinder.getBindingResult();
    }

    public static List<ErrorMessage> validationErrors(BindingResult bindingResult, MessageSource messageSource) {
        return bindingResult
                .getFieldErrors()
                .stream()
                .map(fieldError -> {
                    String fieldErrorCode = fieldError.getCode();
                    String field = fieldError.getField();
                    String[] resolveMessageCodes = bindingResult.resolveMessageCodes(fieldErrorCode);
                    var key = resolveMessageCodes[0] + "." + field;
                    var message = messageSource.getMessage(key, new Object[]{fieldError.getRejectedValue()}, Locale.ENGLISH);
                    return new ErrorMessage(ErrorCode.ofKey(key), message);
                })
                .toList();
    }
}
