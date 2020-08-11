package com.karpowicz.smartbear.britishtimeconverter.converter.time.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultTimeParserTest {
    @Mock
    private Predicate<LocalTime> timePartPredicate;
    @Mock
    private Function<LocalTime, String> timeTextRepresentationFunction;

    @InjectMocks
    private DefaultTimeParser defaultTimeParser;

    @Test
    @DisplayName("When predicate is ok then function should be called")
    void positiveMapping() {
        when(timePartPredicate.test(LocalTime.MIDNIGHT)).thenReturn(true);
        when(timeTextRepresentationFunction.apply(LocalTime.MIDNIGHT)).thenReturn("midnight");

        Optional<String> result = defaultTimeParser.apply(LocalTime.MIDNIGHT);

        assertThat(result, isPresentAndIs("midnight"));
        verify(timePartPredicate).test(LocalTime.MIDNIGHT);
        verify(timeTextRepresentationFunction).apply(LocalTime.MIDNIGHT);
        verifyNoMoreInteractions(timePartPredicate, timeTextRepresentationFunction);
    }

    @Test
    @DisplayName("When predicate is not ok then function should not be called and result should be empty")
    void negativeFlowMapping() {
        when(timePartPredicate.test(LocalTime.MIDNIGHT)).thenReturn(false);

        Optional<String> result = defaultTimeParser.apply(LocalTime.MIDNIGHT);

        assertThat(result, isEmpty());
        verify(timePartPredicate).test(LocalTime.MIDNIGHT);
        verify(timeTextRepresentationFunction, never()).apply(LocalTime.MIDNIGHT);
        verifyNoMoreInteractions(timePartPredicate);
        verifyNoInteractions(timeTextRepresentationFunction);
    }
}