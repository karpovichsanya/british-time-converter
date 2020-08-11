package com.karpowicz.smartbear.britishtimeconverter.converter.time.parser;

import com.karpowicz.smartbear.britishtimeconverter.exception.TimeConverterNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;

/**
 * Factory of {@link TimeConverter}.
 * Based on {@code Map<Locale, TimeConverter>}
 */
@Service
@RequiredArgsConstructor
public class TimeConverterFactory {

    private final Map<Locale, TimeConverter> localeTimeConverterMap;

    /**
     * Gets {@link TimeConverter} linked to {@link Locale}
     *
     * @param locale the {@link Locale} for which should be returned {@link TimeConverter}
     * @return the {@link TimeConverter}
     * @throws TimeConverterNotFoundException when no {@link TimeConverter} found for {@link Locale}
     */
    public TimeConverter get(@NonNull Locale locale) {
        TimeConverter timeConverter = localeTimeConverterMap.get(locale);
        if (timeConverter != null) {
            return timeConverter;
        }
        throw new TimeConverterNotFoundException(String.format("Can't find time converter for %s", locale));
    }
}
