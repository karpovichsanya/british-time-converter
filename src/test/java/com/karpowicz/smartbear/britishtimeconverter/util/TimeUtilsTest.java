package com.karpowicz.smartbear.britishtimeconverter.util;

import com.karpowicz.smartbear.britishtimeconverter.data.TimeData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class TimeUtilsTest {

    @Test
    @DisplayName("24H hour format should be converted to 12H")
    void get12HFormatOfTime() {
        assertThat(TimeUtils.get12hHour(LocalTime.of(13, 0)), is(1));
    }

    @Test
    @DisplayName("12H hour format should be stay the same")
    void get12HFormatOfTimeTheSameResult() {
        assertThat(TimeUtils.get12hHour(LocalTime.of(6, 0)), is(6));
    }

    @Test
    @DisplayName("String in hh:mm format should be converted to LocalTime")
    void successfulConvertToLocalTime() {
        LocalTime actual = TimeUtils.timeDataToLocalTime(new TimeData("09:00"));
        assertThat(actual, is(LocalTime.of(9, 0)));
    }

}