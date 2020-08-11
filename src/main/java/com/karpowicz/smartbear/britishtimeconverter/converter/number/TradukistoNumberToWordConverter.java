package com.karpowicz.smartbear.britishtimeconverter.converter.number;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import pl.allegro.finance.tradukisto.ValueConverters;

/**
 * "Tradukisto" implementation
 * <p>
 * See <a href="https://github.com/allegro/tradukisto">https://github.com/allegro/tradukisto</a>
 */
@Component
public class TradukistoNumberToWordConverter implements NumberToWordConverter {
    private static final ValueConverters VALUE_CONVERTERS = ValueConverters.ENGLISH_INTEGER;

    @Override
    public String convert(@NonNull Integer number) {
        return VALUE_CONVERTERS.asWords(number);
    }
}
