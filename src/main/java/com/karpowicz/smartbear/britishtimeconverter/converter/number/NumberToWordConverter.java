package com.karpowicz.smartbear.britishtimeconverter.converter.number;

/**
 * Number to word converter interface
 */
@FunctionalInterface
public interface NumberToWordConverter {
    /**
     * Converts number -> String
     *
     * @param number to convert
     * @return String representation
     */
    String convert(Integer number);
}
