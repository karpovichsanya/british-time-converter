package com.karpowicz.smartbear.britishtimeconverter;

import com.karpowicz.smartbear.britishtimeconverter.data.TimeData;
import com.karpowicz.smartbear.britishtimeconverter.service.TimeConverterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Integration test of main convertation logic")
class BritishTimeConverterApplicationTest {

    @Autowired
    private TimeConverterService timeConverterService;

    @ParameterizedTest(name = "#{index} - {0} -> {1}")
    @CsvFileSource(resources = "/british_numbers_to_words.csv", numLinesToSkip = 1)
    void checkCorrectMapping(String input, String expected) {
        TimeData timeData = new TimeData(input);
        String actualValue = timeConverterService.convertTimeToWords(timeData);
        assertEquals(expected, actualValue);
    }
}