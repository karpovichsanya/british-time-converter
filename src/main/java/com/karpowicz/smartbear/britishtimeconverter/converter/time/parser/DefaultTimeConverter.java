package com.karpowicz.smartbear.britishtimeconverter.converter.time.parser;

import com.karpowicz.smartbear.britishtimeconverter.exception.TimeParserException;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Chained Time Converter.
 * Goes through {@link TimeParser} for created object and
 * trying to map first not empty TimeParser.
 */
@RequiredArgsConstructor
public class DefaultTimeConverter implements TimeConverter {
    private final List<TimeParser> timeParsers;
    private final Locale locale;

    @Override
    public String convert(LocalTime time) {
        return timeParsers.stream()
                .map(timeParser -> timeParser.apply(time))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new TimeParserException(String.format("Can't parse time %s to text representation", time)));
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
