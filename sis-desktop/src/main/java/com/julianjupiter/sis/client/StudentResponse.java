package com.julianjupiter.sis.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.julianjupiter.sis.model.Student;
import com.julianjupiter.sis.util.OffsetDateTimeDeserializer;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StudentResponse(
        ApiStatus status,
        List<Student> data,
        URI path,
        @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
        OffsetDateTime createdAt) {
}
