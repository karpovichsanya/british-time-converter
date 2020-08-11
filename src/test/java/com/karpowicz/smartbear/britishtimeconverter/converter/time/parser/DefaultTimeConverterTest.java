package com.karpowicz.smartbear.britishtimeconverter.converter.time.parser;

import com.karpowicz.smartbear.britishtimeconverter.exception.TimeParserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefaultTimeConverterTest {
    @Test
    @DisplayName("Assert that DefaultTimeConverter throws TimeParserException when can't parse time")
    void defaultTimeConverterShouldThrowTimeParserException() {
        DefaultTimeConverter defaultTimeConverter = new DefaultTimeConverter(emptyList(), Locale.UK);
        assertThrows(TimeParserException.class, () -> defaultTimeConverter.convert(LocalTime.MIDNIGHT));
    }

    @Test
    @DisplayName("Assert that DefaultTimeConverter is converting first not empty time parser")
    void firstNonEmptyTimeParserWins() {
        List<TimeParser> timeParsers = new ArrayList<>();
        timeParsers.add(new DefaultTimeParser(LocalTime.MIDNIGHT::equals, (time) -> "midnight"));
        timeParsers.add(new DefaultTimeParser(LocalTime.NOON::equals, (time) -> "noon"));

        DefaultTimeConverter defaultTimeConverter = new DefaultTimeConverter(timeParsers, Locale.UK);
        String result = defaultTimeConverter.convert(LocalTime.MIDNIGHT);
        assertThat(result, is("midnight"));
    }

    @Test
    @DisplayName("Assert that DefaultTimeConverter is converting first not empty time parser, second wins")
    void secondNonEmptyTimeParserWins() {
        List<TimeParser> timeParsers = new ArrayList<>();
        timeParsers.add(new DefaultTimeParser(LocalTime.MIDNIGHT::equals, (time) -> "midnight"));
        timeParsers.add(new DefaultTimeParser(LocalTime.NOON::equals, (time) -> "noon"));

        DefaultTimeConverter defaultTimeConverter = new DefaultTimeConverter(timeParsers, Locale.UK);
        String result = defaultTimeConverter.convert(LocalTime.NOON);
        assertThat(result, is("noon"));
    }

    @Test
    @DisplayName("Assert that DefaultTimeConverter returns correct locale")
    void correctLocaleShouldBeReturned() {
        DefaultTimeConverter defaultTimeConverter = new DefaultTimeConverter(null, Locale.UK);
        assertThat(defaultTimeConverter.getLocale(), is(Locale.UK));
    }

}