package com.karpowicz.smartbear.britishtimeconverter.data;

import lombok.Value;

import java.util.Locale;

/**
 * Simple data transfer class.
 */
@Value
public class TimeData {
    Locale locale = Locale.UK;
    String timeString;
}
