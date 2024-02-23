package com.julianjupiter.sis;

import java.util.Locale;

public record Config(
        String applicationName,
        String sisServiceBaseUrl,
        String messages,
        Locale locale) {}