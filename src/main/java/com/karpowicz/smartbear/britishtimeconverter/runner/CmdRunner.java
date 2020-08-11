package com.karpowicz.smartbear.britishtimeconverter.runner;

import com.karpowicz.smartbear.britishtimeconverter.data.TimeData;
import com.karpowicz.smartbear.britishtimeconverter.service.TimeConverterService;
import com.karpowicz.smartbear.britishtimeconverter.validator.InputValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

/**
 * Simple console application to test time converter
 * Usage:
 * <ol>
 *     <li>Enter time in HH:mm format</li>
 *     <li>Enjoy</li>
 *     <li>Enter 'exit' to exit</li>
 * </ol>
 */
@Slf4j
@Component
@Profile("!test")
@RequiredArgsConstructor
public class CmdRunner implements CommandLineRunner {

    private final InputValidator cmdInputValidator;
    private final TimeConverterService timeConverterService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        printToConsole("British time converter.");
        printToConsole("To exit, please enter 'exit'");
        enterTimeMessage();
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals("exit")) {
                break;
            }
            Optional<String> validationResult = cmdInputValidator.validate(input);
            if (!validationResult.isPresent()) {
                String timeInWords = timeConverterService.convertTimeToWords(new TimeData(input));
                printToConsole(timeInWords);
            } else {
                printToConsole(validationResult.get());
                printToConsole("Please, check your input");
            }
            enterTimeMessage();
        }
    }

    private void enterTimeMessage() {
        printToConsole("");
        printToConsole("Please enter time in HH:mm format.");
    }

    private void printToConsole(String text) {
        System.out.println(text);
    }
}
