package ru.feodorkek.dev.mcremapper.service.impl;

import org.springframework.stereotype.Service;
import ru.feodorkek.dev.mcremapper.service.DateTimeService;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class DateTimeServiceImpl implements DateTimeService {

    @Override
    public String instantDifferenceToHoursMinutesSecondsFormat(final Instant start, final Instant end) {
        final var duration = Duration.between(start, end);
        return secondsToHoursMinutesSecondsFormat(Math.abs(duration.toSeconds()));
    }

    @Override
    public String secondsToHoursMinutesSecondsFormat(final long seconds) {
        final var duration = Duration.ofSeconds(seconds);
        final var hours = duration.toHours();
        final var minutes = duration.toMinutes() % 60;
        final var remainingSeconds = duration.getSeconds() % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

    @Override
    public DateTimeFormatter daysMonthYearFormatter() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    @Override
    public DateTimeFormatter hoursMinutesFormatter() {
        return DateTimeFormatter.ofPattern("HH:mm");
    }

    @Override
    public Instant instantNowWithTruncatedSeconds() {
        return Instant.now().truncatedTo(ChronoUnit.SECONDS);
    }

}
