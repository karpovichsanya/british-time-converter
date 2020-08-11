package com.karpowicz.smartbear.britishtimeconverter.validator;

import java.util.Optional;

/**
 * Represents validation interface.
 * Designed to perform validation logic on {@code cmd} input string
 */
public interface InputValidator {
    /**
     * @param input The String to validate
     * @return {@code java.util.Optional<String>} with validation error message
     */
    Optional<String> validate(String input);
}
