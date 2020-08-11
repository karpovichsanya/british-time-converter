package com.karpowicz.smartbear.britishtimeconverter.validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Chained Input validator.
 * Performs validation for all available {@link InputValidator} classes in Spring Context.
 * Fail-fast validation, meaning validation will be failed at the first failed {@link InputValidator},
 * with their validation error message as an {@code output}.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CmdInputValidator implements InputValidator {
    private final List<InputValidator> inputValidators;

    @Override
    public Optional<String> validate(String input) {
        return inputValidators.stream()
                .filter(inputValidator -> !(inputValidator instanceof CmdInputValidator))
                .map(inputValidator -> inputValidator.validate(input))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }
}
