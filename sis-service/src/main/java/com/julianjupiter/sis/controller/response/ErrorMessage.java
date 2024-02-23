package com.julianjupiter.sis.controller.response;

import com.julianjupiter.sis.util.ErrorCode;

public record ErrorMessage(ErrorCode code, String message) {
}
