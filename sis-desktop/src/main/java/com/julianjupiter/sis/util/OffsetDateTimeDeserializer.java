package com.julianjupiter.sis.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class OffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {
    private static final System.Logger LOGGER = System.getLogger(OffsetDateTimeDeserializer.class.getName());

    @Override
    public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String offsetDateTimeString = jsonParser.getText();
        try {
            return OffsetDateTime.parse(offsetDateTimeString, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        } catch (DateTimeParseException exception) {
            LOGGER.log(System.Logger.Level.ERROR, exception::getMessage);
            return null;
        }
    }
}
