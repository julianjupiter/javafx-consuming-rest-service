package com.julianjupiter.sis.controller.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateStudentRequest(
        @NotBlank
        String lastName,
        @NotBlank
        String firstName,
        String middleName) {
}