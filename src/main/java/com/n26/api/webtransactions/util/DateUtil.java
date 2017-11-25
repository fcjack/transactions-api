package com.n26.api.webtransactions.util;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    public static boolean olderThenLimit(long value, ChronoUnit unit, long duration) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneOffset.UTC);
        Instant instant = Instant.ofEpochMilli(value);
        ZonedDateTime receivedValue = ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);

        long diff = ChronoUnit.SECONDS.between(zonedDateTime, receivedValue);
        if (diff < 0) diff = Math.abs(diff);

        return diff > duration;
    }
}
