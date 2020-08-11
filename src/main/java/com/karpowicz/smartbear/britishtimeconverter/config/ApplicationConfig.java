package com.karpowicz.smartbear.britishtimeconverter.config;

import com.karpowicz.smartbear.britishtimeconverter.converter.time.parser.TimeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Configuration
@Import(BritishConverterConfiguration.class)
public class ApplicationConfig {

    /**
     * Keeps all possible {@link TimeConverter} groupped by {@link Locale}
     *
     * @param timeConverterList {@code java.util.List<TimeConverter>}
     * @return {@code java.util.Map<Locale, TimeConverter>}
     */
    @Bean
    public Map<Locale, TimeConverter> localeTimeConverterMap(List<TimeConverter> timeConverterList) {
        return timeConverterList.stream()
                .collect(toMap(TimeConverter::getLocale, Function.identity()));
    }
}
