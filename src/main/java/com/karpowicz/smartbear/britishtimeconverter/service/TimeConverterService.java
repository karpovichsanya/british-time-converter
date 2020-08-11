package com.karpowicz.smartbear.britishtimeconverter.service;

import com.karpowicz.smartbear.britishtimeconverter.converter.time.parser.TimeConverter;
import com.karpowicz.smartbear.britishtimeconverter.converter.time.parser.TimeConverterFactory;
import com.karpowicz.smartbear.britishtimeconverter.data.TimeData;
import com.karpowicz.smartbear.britishtimeconverter.util.TimeUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimeConverterService {
    private final TimeConverterFactory timeConverterFactory;

    public String convertTimeToWords(@NonNull TimeData timeData) {
        TimeConverter timeConverter = timeConverterFactory.get(timeData.getLocale());
        String timeInWords = timeConverter.convert(TimeUtils.timeDataToLocalTime(timeData));
        log.info("{} is {}", timeData.getTimeString(), timeInWords);
        return timeInWords;
    }
}
