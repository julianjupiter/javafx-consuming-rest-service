package com.julianjupiter.sis.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.julianjupiter.sis.util.OffsetDateTimeDeserializer;

import java.time.OffsetDateTime;

public record Student(
        Long id,
        String lastName,
        String firstName,
        String middleName,
        @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
        OffsetDateTime createdAt,
        @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
        OffsetDateTime updatedAt) {
}
