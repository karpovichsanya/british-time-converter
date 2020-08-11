package com.karpowicz.smartbear.britishtimeconverter.converter.time.parser;

import java.time.LocalTime;
import java.util.Locale;

/**
 * Main converter interface.
 * Designed to keep main convertation logic.
 * By aggregation all possible {@link TimeParser}
 */
public interface TimeConverter {
    /**
     * Main logic goes here.
     *
     * @param time the
     * @return converted {@code string}
     */
    String convert(LocalTime time);

    /**
     * @return the {@link Locale} of object
     */
    Locale getLocale();
}
