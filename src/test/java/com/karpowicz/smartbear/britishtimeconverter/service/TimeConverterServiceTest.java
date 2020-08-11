package com.karpowicz.smartbear.britishtimeconverter.service;

import com.karpowicz.smartbear.britishtimeconverter.converter.time.parser.TimeConverter;
import com.karpowicz.smartbear.britishtimeconverter.converter.time.parser.TimeConverterFactory;
import com.karpowicz.smartbear.britishtimeconverter.data.TimeData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimeConverterServiceTest {

    @Mock
    private TimeConverterFactory timeConverterFactory;
    @Mock
    private TimeConverter timeConverter;

    @InjectMocks
    private TimeConverterService timeConverterService;

    @Test
    @DisplayName("Verify that time converter is executed only once")
    void verifyTimeConverterIsExecuted() {
        when(timeConverterFactory.get(Locale.UK)).thenReturn(timeConverter);
        when(timeConverter.convert(LocalTime.MIDNIGHT)).thenReturn("converted!");

        String result = timeConverterService.convertTimeToWords(new TimeData("00:00"));

        assertThat(result, is("converted!"));
        verify(timeConverterFactory).get(Locale.UK);
        verify(timeConverter).convert(LocalTime.MIDNIGHT);
        verifyNoMoreInteractions(timeConverterFactory, timeConverter);
    }
}