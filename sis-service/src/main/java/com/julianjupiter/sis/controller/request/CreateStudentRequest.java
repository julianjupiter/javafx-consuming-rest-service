package com.julianjupiter.sis.controller.request;

import jakarta.validation.constraints.NotBlank;

public record CreateStudentRequest(
        @NotBlank
        String lastName,
        @NotBlank
        String firstName,
        String middleName) {
}
