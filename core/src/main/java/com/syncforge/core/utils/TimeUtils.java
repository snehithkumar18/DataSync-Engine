package com.syncforge.core.utils;

import com.syncforge.core.exceptions.ValidationException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TimeUtils provides high-integrity temporal utilities for the SyncForge engine.
 * It contains a precision nanosecond timer, a compound duration parser (e.g. "1h 30m"), 
 * and UTC-centric ISO-8601 timestamp formatter/parsers.
 */
public final class TimeUtils {

    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_INSTANT;
    
    // Pattern to match compound durations: e.g. "1d 2h 30m 15s 500ms"
    private static final Pattern DURATION_PATTERN = Pattern.compile(
        "^\\s*" +
        "(?:(\\d+)\\s*d(?:ays?)?)?\\s*" +
        "(?:(\\d+)\\s*h(?:ours?)?)?\\s*" +
        "(?:(\\d+)\\s*m(?:in(?:utes?)?)?)?\\s*" +
        "(?:(\\d+)\\s*s(?:ec(?:onds?)?)?)?\\s*" +
        "(?:(\\d+)\\s*ms)?\\s*$", 
        Pattern.CASE_INSENSITIVE
    );

    /**
     * Prevents instantiation of this utility class.
     */
    private TimeUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Converts a millisecond epoch timestamp into a standardized ISO-8601 UTC string.
     *
     * @param epochMillis the millisecond epoch timestamp. Must be &gt;= 0.
     * @return the formatted ISO-8601 UTC string.
     * @throws IllegalArgumentException if epochMillis is negative.
     */
    public static String toIsoString(long epochMillis) {
        if (epochMillis < 0) {
            throw new IllegalArgumentException("Epoch milliseconds cannot be negative: " + epochMillis);
        }
        return ISO_FORMATTER.format(Instant.ofEpochMilli(epochMillis));
    }

    /**
     * Converts an {@link Instant} into a standardized ISO-8601 UTC string.
     *
     * @param instant the instant to format. Must not be null.
     * @return the formatted ISO-8601 UTC string.
     * @throws NullPointerException if instant is null.
     */
    public static String toIsoString(Instant instant) {
        Objects.requireNonNull(instant, "Instant must not be null");
        return ISO_FORMATTER.format(instant);
    }

    /**
     * Parses a standardized ISO-8601 UTC timestamp string into an {@link Instant}.
     *
     * @param isoStr the ISO-8601 timestamp to parse. Must not be null or blank.
     * @return the parsed {@link Instant}.
     * @throws NullPointerException if isoStr is null.
     * @throws ValidationException  if the format is invalid or cannot be parsed.
     */
    public static Instant parseIsoTimestamp(String isoStr) {
        Objects.requireNonNull(isoStr, "ISO timestamp string must not be null");
        if (isoStr.isBlank()) {
            throw new ValidationException("ISO timestamp string must not be empty or blank");
        }
        try {
            return Instant.parse(isoStr);
        } catch (DateTimeParseException e) {
            throw new ValidationException("Invalid ISO-8601 timestamp: '" + isoStr + "'", e);
        }
    }

    /**
     * Parses a compound duration string (e.g. "1d 2h", "90m", "10s 500ms") and returns its value in milliseconds.
     * Supported suffixes are: d (days), h (hours), m (minutes), s (seconds), ms (milliseconds).
     *
     * @param durationStr the duration string. Must not be null or blank.
     * @return the parsed duration in milliseconds.
     * @throws NullPointerException if durationStr is null.
     * @throws ValidationException  if the format is invalid or empty.
     */
    public static long parseDuration(String durationStr) {
        Objects.requireNonNull(durationStr, "Duration string must not be null");
        if (durationStr.isBlank()) {
            throw new ValidationException("Duration string must not be empty or blank");
        }

        String trimmed = durationStr.trim();
        Matcher matcher = DURATION_PATTERN.matcher(trimmed);
        if (!matcher.matches() || trimmed.isEmpty()) {
            throw new ValidationException("Invalid duration format: '" + durationStr + 
                    "'. Expected format e.g. '1d 2h 30m' or '10s'");
        }

        long totalMillis = 0;

        String daysStr = matcher.group(1);
        String hoursStr = matcher.group(2);
        String minutesStr = matcher.group(3);
        String secondsStr = matcher.group(4);
        String millisStr = matcher.group(5);

        // Track if at least one component was matched
        boolean matchedAny = false;

        if (daysStr != null) {
            totalMillis += Long.parseLong(daysStr) * 24 * 60 * 60 * 1000L;
            matchedAny = true;
        }
        if (hoursStr != null) {
            totalMillis += Long.parseLong(hoursStr) * 60 * 60 * 1000L;
            matchedAny = true;
        }
        if (minutesStr != null) {
            totalMillis += Long.parseLong(minutesStr) * 60 * 1000L;
            matchedAny = true;
        }
        if (secondsStr != null) {
            totalMillis += Long.parseLong(secondsStr) * 1000L;
            matchedAny = true;
        }
        if (millisStr != null) {
            totalMillis += Long.parseLong(millisStr);
            matchedAny = true;
        }

        if (!matchedAny) {
            throw new ValidationException("Invalid duration format: '" + durationStr + "' matched no known time units.");
        }

        return totalMillis;
    }

    /**
     * PrecisionTimer measures elapsed times using high-resolution nanosecond clock.
     */
    public static final class PrecisionTimer {
        private long startTimeNanos;

        private PrecisionTimer() {
            this.startTimeNanos = System.nanoTime();
        }

        /**
         * Starts a new precision timer.
         *
         * @return a new active PrecisionTimer.
         */
        public static PrecisionTimer start() {
            return new PrecisionTimer();
        }

        /**
         * Resets the timer's starting point to the current system nanosecond tick.
         */
        public void reset() {
            this.startTimeNanos = System.nanoTime();
        }

        /**
         * Returns the elapsed time in nanoseconds since the timer was started or reset.
         *
         * @return elapsed nanoseconds.
         */
        public long elapsedNanos() {
            return System.nanoTime() - startTimeNanos;
        }

        /**
         * Returns the elapsed time in milliseconds since the timer was started or reset.
         *
         * @return elapsed milliseconds.
         */
        public double elapsedMillis() {
            return elapsedNanos() / 1_000_000.0;
        }

        /**
         * Formats the elapsed time into a human-friendly string.
         *
         * @return formatted duration string (e.g. "1.23 ms", "5.43 s").
         */
        public String format() {
            long nanos = elapsedNanos();
            if (nanos < 1_000) {
                return nanos + " ns";
            }
            if (nanos < 1_000_000) {
                return String.format("%.2f µs", nanos / 1000.0);
            }
            if (nanos < 1_000_000_000L) {
                return String.format("%.2f ms", nanos / 1_000_000.0);
            }
            return String.format("%.3f s", nanos / 1_000_000_000.0);
        }

        @Override
        public String toString() {
            return format();
        }
    }
}
