package com.karpowicz.smartbear.britishtimeconverter.converter.time.parser;

import java.time.LocalTime;
import java.util.Optional;
import java.util.function.Function;

/**
 * Simple interface to map {@link LocalTime} to {@link String} based on {@link Function}.
 * Main goal of this interface, is to be the smallest logical part( or chain, if you will)
 * of {@link LocalTime} to {@link String}.
 * <p>
 * Used in {@link DefaultTimeConverter}
 */
@FunctionalInterface
public interface TimeParser extends Function<LocalTime, Optional<String>> {
}
