package com.karpowicz.smartbear.britishtimeconverter.converter.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TradukistoNumberToWordConverterTest {

    @Test
    @DisplayName("Assert Tradukisto library works")
    void checkIsLibWorks() {
        TradukistoNumberToWordConverter lib = new TradukistoNumberToWordConverter();
        assertThat(lib.convert(1), is("one"));
    }

    @Test
    @DisplayName("Should throw NullPointerException when null is passed")
    void expectNullPointer() {
        assertThrows(NullPointerException.class, () -> new TradukistoNumberToWordConverter().convert(null));
    }
}