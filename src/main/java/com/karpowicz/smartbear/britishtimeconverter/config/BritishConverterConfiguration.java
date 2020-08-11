package com.karpowicz.smartbear.britishtimeconverter.config;

import com.karpowicz.smartbear.britishtimeconverter.converter.number.NumberToWordConverter;
import com.karpowicz.smartbear.britishtimeconverter.converter.time.parser.DefaultTimeConverter;
import com.karpowicz.smartbear.britishtimeconverter.converter.time.parser.DefaultTimeParser;
import com.karpowicz.smartbear.britishtimeconverter.converter.time.parser.TimeConverter;
import com.karpowicz.smartbear.britishtimeconverter.converter.time.parser.TimeParser;
import com.karpowicz.smartbear.britishtimeconverter.util.TimeUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Configuration
public class BritishConverterConfiguration {

    private final NumberToWordConverter numberToWordConverter;

    public BritishConverterConfiguration(@Qualifier("tradukistoNumberToWordConverter") NumberToWordConverter numberToWordConverter) {
        this.numberToWordConverter = numberToWordConverter;
    }

    @Bean
    public TimeConverter britishTimeConverter() {
        return new DefaultTimeConverter(britishTimeParsers(), Locale.UK);
    }

    private List<TimeParser> britishTimeParsers() {
        ArrayList<TimeParser> timeParsers = new ArrayList<>();
        timeParsers.add(midnightTimeParser());
        timeParsers.add(noonTimeParser());
        timeParsers.add(oclockTimeParser());
        timeParsers.add(quarterPastTimeParser());
        timeParsers.add(halfPastTimeParser());
        timeParsers.add(midnightPastTimeParser());
        timeParsers.add(exactPastTimeParser());
        timeParsers.add(quarterToParser());
        timeParsers.add(exactToTimeParser());
        timeParsers.add(exactTimeParser());
        return timeParsers;
    }

    private TimeParser midnightTimeParser() {
        return new DefaultTimeParser(LocalTime.MIDNIGHT::equals, (time) -> "midnight");
    }

    private TimeParser noonTimeParser() {
        return new DefaultTimeParser(LocalTime.NOON::equals, (time) -> "noon");
    }

    private TimeParser oclockTimeParser() {
        return new DefaultTimeParser(
                (time) -> time.getMinute() == 0,
                (time) -> numberToWordConverter.convert(TimeUtils.get12hHour(time)) + " o' clock"
        );
    }

    private TimeParser quarterPastTimeParser() {
        return new DefaultTimeParser(
                (time) -> time.getMinute() == 15,
                (time) -> "quarter past " + numberToWordConverter.convert(TimeUtils.get12hHour(time))
        );
    }

    private TimeParser halfPastTimeParser() {
        return new DefaultTimeParser(
                (time) -> time.getMinute() == 30,
                (time) -> "half past " + numberToWordConverter.convert(TimeUtils.get12hHour(time))
        );
    }

    private TimeParser midnightPastTimeParser() {
        return new DefaultTimeParser(
                (time) -> time.getHour() == 0 && time.getMinute() < 30 && time.getMinute() % 5 == 0,
                (time) -> numberToWordConverter.convert(time.getMinute())
                        + " past midnight");
    }

    private TimeParser exactPastTimeParser() {
        return new DefaultTimeParser(
                (time) -> time.getMinute() < 30 && time.getMinute() % 5 == 0,
                (time) -> numberToWordConverter.convert(time.getMinute())
                        + " past " + numberToWordConverter.convert(TimeUtils.get12hHour(time))
        );
    }

    private TimeParser quarterToParser() {
        return new DefaultTimeParser(
                (time) -> time.getMinute() == 45,
                (time) -> "quarter to " + numberToWordConverter.convert(TimeUtils.get12hHour(time.plusHours(1)))
        );
    }

    private TimeParser exactToTimeParser() {
        return new DefaultTimeParser(
                (time) -> time.getMinute() > 30 && time.getMinute() % 5 == 0,
                (time) -> numberToWordConverter.convert(TimeUtils.getMinutesTo(time))
                        + " to " + numberToWordConverter.convert(TimeUtils.get12hHour(time.plusHours(1)))
        );
    }

    private TimeParser exactTimeParser() {
        return new DefaultTimeParser(
                (time) -> time.getMinute() % 5 != 0,
                (time) -> numberToWordConverter.convert(TimeUtils.get12hHour(time))
                        + " " + numberToWordConverter.convert(time.getMinute())
        );
    }
}
