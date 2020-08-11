package com.karpowicz.smartbear.britishtimeconverter.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;

class CmdInputValidatorTest {

    @Test
    @DisplayName("Assert that CmdInputValidator is skipped in the validation chain")
    void CmdInputValidatorShouldBeSkipped() {
        CmdInputValidator cmdInputValidator = new CmdInputValidator(singletonList(new CmdInputValidator(null)));
        Optional<String> result = cmdInputValidator.validate("1234");
        assertThat(result, isEmpty());
    }

    @Test
    @DisplayName("Assert that validation is failed at the first failed validator")
    void failFastValidationCheck() {
        List<InputValidator> inputValidators = new ArrayList<>();
        inputValidators.add(new TimeFormatValidator());
        inputValidators.add(input -> {
            if (input.isEmpty()) {
                return Optional.of("not reachable validator");
            }
            return Optional.empty();
        });
        CmdInputValidator cmdInputValidator = new CmdInputValidator(inputValidators);
        Optional<String> result = cmdInputValidator.validate("1234");
        assertThat(result, isPresentAndIs("Can't parse provided time 1234"));
    }

    @Test
    @DisplayName("Assert that validation is failed at the first failed validator, second validator should failed")
    void failFastValidationPart2Check() {
        List<InputValidator> inputValidators = new ArrayList<>();
        inputValidators.add(new TimeFormatValidator());
        inputValidators.add(input -> {
            if (input.length() == 5) {
                return Optional.of("second fake validator");
            }
            return Optional.empty();
        });
        CmdInputValidator cmdInputValidator = new CmdInputValidator(inputValidators);
        Optional<String> result = cmdInputValidator.validate("12:34");
        assertThat(result, isPresentAndIs("second fake validator"));
    }
}