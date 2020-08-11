package com.karpowicz.smartbear.britishtimeconverter.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static org.hamcrest.MatcherAssert.assertThat;

class TimeFormatValidatorTest {

    @Test
    @DisplayName("Assert that validation is passed when time is in hh:mm format")
    void successfulValidation() {
        TimeFormatValidator timeFormatValidator = new TimeFormatValidator();
        Optional<String> validate = timeFormatValidator.validate("12:00");
        assertThat(validate, isEmpty());
    }

    @Test
    @DisplayName("Assert that validation is failed when time is not in hh:mm format")
    void timeIsNotInHHmmFormatValidation() {
        TimeFormatValidator timeFormatValidator = new TimeFormatValidator();
        Optional<String> validate = timeFormatValidator.validate("123:00");
        assertThat(validate, isPresentAndIs("Can't parse provided time 123:00"));
    }

    @Test
    @DisplayName("Assert that validation is failed when time is not in correct range")
    void timeIsNotInCorrectRangeValidation() {
        TimeFormatValidator timeFormatValidator = new TimeFormatValidator();
        Optional<String> validate = timeFormatValidator.validate("99:00");
        assertThat(validate, isPresentAndIs("Can't parse provided time 99:00"));
    }

    @Test
    @DisplayName("Assert that validation is failed when time is not null")
    void timeIsNullValidation() {
        TimeFormatValidator timeFormatValidator = new TimeFormatValidator();
        Optional<String> validate = timeFormatValidator.validate(null);
        assertThat(validate, isPresentAndIs("Can't parse provided time null"));
    }
}