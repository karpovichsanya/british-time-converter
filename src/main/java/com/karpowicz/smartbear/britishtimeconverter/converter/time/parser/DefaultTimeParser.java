package com.karpowicz.smartbear.britishtimeconverter.converter.time.parser;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Default implementation of {@link TimeParser}.
 * Decoupled concrete time to string converter.
 * Depends on {@link Predicate} {@code timePartPredicate}
 * and {@link Function} timeTextRepresentationFunction.
 * <p>
 * Example:
 * <pre>
 *    TimeParser midnightParser = new DefaultTimeParser(
 *                                        LocalTime.MIDNIGHT::equals,
 *                                        (time) -> "midnight"
 *                                    );
 *   assertTrue("midnight", midnightParser.apply(LocalTime.MIDNIGHT));
 * </pre>
 */
@RequiredArgsConstructor
public class DefaultTimeParser implements TimeParser {
    private final Predicate<LocalTime> timePartPredicate;
    private final Function<LocalTime, String> timeTextRepresentationFunction;

    /**
     * @param time the {@link LocalTime}
     * @return the {@code java.util.Optional<String>} result of convertation
     */
    @Override
    public Optional<String> apply(@NonNull LocalTime time) {
        if (timePartPredicate.test(time)) {
            return Optional.of(timeTextRepresentationFunction.apply(time));
        }
        return Optional.empty();
    }
}
