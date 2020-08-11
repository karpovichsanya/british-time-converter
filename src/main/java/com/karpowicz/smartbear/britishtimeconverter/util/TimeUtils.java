package com.karpowicz.smartbear.britishtimeconverter.util;

import com.karpowicz.smartbear.britishtimeconverter.data.TimeData;
import lombok.NonNull;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoField.HOUR_OF_AMPM;

/**
 * Utility to convert time based objects
 */
public class TimeUtils {
    private static final String HH_MM_PATTERN = "H[H]:mm";
    private static final int ONE_HOUR_MINUTES = 60;

    private TimeUtils() {
    }

    /**
     * Converts {@link TimeData} time in {@code FormatStyle.SHORT}string to their
     * {@link java.util.Locale} based {@link LocalTime} representation
     *
     * @param timeData The {@link TimeData} object
     * @return {@link java.util.Locale} based {@link LocalTime} representation.
     */
    public static LocalTime timeDataToLocalTime(@NonNull TimeData timeData) {
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(HH_MM_PATTERN)
                .withLocale(timeData.getLocale());
        return LocalTime.parse(timeData.getTimeString(), formatter);
    }

    /**
     * Converts 24H {@link LocalTime} to their 12H format
     *
     * @param time The {@link LocalTime}
     * @return AM/PM based hours
     */
    public static int get12hHour(@NonNull LocalTime time) {
        if (time.getHour() == 12) {
            return 12;
        }
        return (int) time.getLong(HOUR_OF_AMPM);
    }

    /**
     * Gets minutes to full hour
     *
     * @param time The {@link LocalTime}
     * @return 60 - minutes
     */
    public static int getMinutesTo(@NonNull LocalTime time) {
        return ONE_HOUR_MINUTES - time.getMinute();
    }
}
