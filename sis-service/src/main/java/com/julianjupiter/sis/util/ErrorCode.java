package com.julianjupiter.sis.util;

import java.util.Arrays;

public enum ErrorCode {
    ERR404("not.found"),
    ERR500("generic.error"),
    ERR600("NotBlank.createStudentRequest.lastName"),
    ERR601("NotBlank.createStudentRequest.firstName"),
    ERR602("NotBlank.updateStudentRequest.lastName"),
    ERR603("NotBlank.updateStudentRequest.firstName");

    private final String key;

    ErrorCode(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    public static ErrorCode ofKey(String key) {
        return Arrays.stream(values())
                .filter(errorCode -> errorCode.key.equals(key))
                .findFirst()
                .orElse(ErrorCode.ERR500);
    }
}
