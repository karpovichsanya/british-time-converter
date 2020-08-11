package com.karpowicz.smartbear.britishtimeconverter.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Time based validation class.
 * Validates specified {@code input} string with HH:mm time format
 */
@Slf4j
@Component
public class TimeFormatValidator implements InputValidator {
    public static final String HH_MM_REGEX = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]";

    @Override
    public Optional<String> validate(String input) {
        if (input == null || !input.matches(HH_MM_REGEX)) {
            log.info("Validation failed for {}", input);
            return Optional.of(String.format("Can't parse provided time %s", input));
        }
        return Optional.empty();
    }
}
