package com.karpowicz.smartbear.britishtimeconverter.converter.time.parser;

import com.karpowicz.smartbear.britishtimeconverter.exception.TimeConverterNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimeConverterFactoryTest {

    @Mock
    private Map<Locale, TimeConverter> localeTimeConverterMap;
    @Mock
    private TimeConverter timeConverter;

    @InjectMocks
    private TimeConverterFactory timeConverterFactory;

    @Test
    @DisplayName("Factory should return concrete TimeConverter by Locale")
    void returnConcreteTimeConverterByLocale() {
        when(localeTimeConverterMap.get(Locale.UK)).thenReturn(timeConverter);
        TimeConverter result = timeConverterFactory.get(Locale.UK);
        assertThat(result, is(timeConverter));
        verify(localeTimeConverterMap).get(Locale.UK);
        verifyNoMoreInteractions(localeTimeConverterMap);
    }

    @Test
    @DisplayName("Expect exception when no converter found for locale")
    void expectException() {
        when(localeTimeConverterMap.get(Locale.UK)).thenReturn(null);

        assertThrows(TimeConverterNotFoundException.class, () -> timeConverterFactory.get(Locale.UK));
        verify(localeTimeConverterMap).get(Locale.UK);
        verifyNoMoreInteractions(localeTimeConverterMap);
    }

    @Test
    @DisplayName("Should throw NullPointerException when null is passed")
    void expectNullPointer() {
        assertThrows(NullPointerException.class, () -> timeConverterFactory.get(null));
    }
}