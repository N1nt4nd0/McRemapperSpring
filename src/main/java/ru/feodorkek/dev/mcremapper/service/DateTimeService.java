package ru.feodorkek.dev.mcremapper.service;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public interface DateTimeService {

    String instantDifferenceToHoursMinutesSecondsFormat(Instant start, Instant end);

    String secondsToHoursMinutesSecondsFormat(long seconds);

    DateTimeFormatter daysMonthYearFormatter();

    DateTimeFormatter hoursMinutesFormatter();

    Instant instantNowWithTruncatedSeconds();

}
