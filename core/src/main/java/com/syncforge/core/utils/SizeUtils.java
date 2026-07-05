package com.syncforge.core.utils;

import com.syncforge.core.exceptions.ValidationException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SizeUtils provides utilities for converting raw byte sizes to standardized human-readable strings 
 * (supporting decimal SI and binary IEC systems) and parsing configured sizes (e.g., "500MB", "2.5 GB")
 * back into raw byte quantities.
 * 
 * <p>All inputs are validated. Negative sizes are rejected with exception feedback.</p>
 */
public final class SizeUtils {

    private static final long IEC_BASE = 1024;
    private static final long SI_BASE = 1000;

    private static final String[] IEC_UNITS = {"B", "KiB", "MiB", "GiB", "TiB", "PiB", "EiB"};
    private static final String[] SI_UNITS = {"B", "KB", "MB", "GB", "TB", "PB", "EB"};

    private static final Pattern SIZE_PATTERN = Pattern.compile("^\\s*(\\d+(?:\\.\\d+)?)\\s*([a-zA-Z]*)\\s*$");

    /**
     * Prevents instantiation of this utility class.
     */
    private SizeUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Formats a byte size into a human-readable string using the default binary IEC representation (1024 base)
     * with two decimal places.
     *
     * @param bytes the byte quantity to format. Must be &gt;= 0.
     * @return a formatted human-readable size string.
     * @throws IllegalArgumentException if bytes is negative.
     */
    public static String toHumanReadable(long bytes) {
        return toHumanReadable(bytes, false, "%.2f %s");
    }

    /**
     * Formats a byte size into a human-readable string.
     *
     * @param bytes the byte quantity to format. Must be &gt;= 0.
     * @param useSi if true, uses decimal SI units (1000 base, e.g. KB, MB); if false, uses binary IEC units (1024 base, e.g. KiB, MiB).
     * @return a formatted human-readable size string.
     * @throws IllegalArgumentException if bytes is negative.
     */
    public static String toHumanReadable(long bytes, boolean useSi) {
        return toHumanReadable(bytes, useSi, "%.2f %s");
    }

    /**
     * Formats a byte size into a human-readable string with custom formatting.
     *
     * @param bytes   the byte quantity to format. Must be &gt;= 0.
     * @param useSi   if true, uses decimal SI units (1000 base); if false, uses binary IEC units (1024 base).
     * @param formatSpecifier the format specifier string (e.g. "%.2f %s"). Must not be null or blank.
     * @return a formatted human-readable size string.
     * @throws IllegalArgumentException if bytes is negative.
     * @throws NullPointerException     if formatSpecifier is null.
     */
    public static String toHumanReadable(long bytes, boolean useSi, String formatSpecifier) {
        if (bytes < 0) {
            throw new IllegalArgumentException("Byte size cannot be negative: " + bytes);
        }
        Objects.requireNonNull(formatSpecifier, "Format specifier must not be null");
        if (formatSpecifier.isBlank()) {
            throw new IllegalArgumentException("Format specifier must not be empty or blank");
        }

        long base = useSi ? SI_BASE : IEC_BASE;
        String[] units = useSi ? SI_UNITS : IEC_UNITS;

        if (bytes < base) {
            return String.format(formatSpecifier, (double) bytes, units[0]);
        }

        int exponent = (int) (Math.log(bytes) / Math.log(base));
        // Guard against index out of bounds for exceptionally large byte inputs
        exponent = Math.min(exponent, units.length - 1);
        
        double value = bytes / Math.pow(base, exponent);
        return String.format(formatSpecifier, value, units[exponent]);
    }

    /**
     * Parses a configured human-readable size string (e.g., "500MB", "2.5 GiB", "1024") into bytes.
     * Supported units are case-insensitive and encompass both IEC (KiB, MiB, etc.) and SI (KB, MB, etc.).
     *
     * @param sizeStr the string to parse. Must not be null or blank.
     * @return the parsed byte count.
     * @throws NullPointerException     if sizeStr is null.
     * @throws ValidationException      if the format is invalid or references unsupported units.
     */
    public static long parseSize(String sizeStr) {
        Objects.requireNonNull(sizeStr, "Size string must not be null");
        if (sizeStr.isBlank()) {
            throw new ValidationException("Size string must not be empty or blank");
        }

        Matcher matcher = SIZE_PATTERN.matcher(sizeStr);
        if (!matcher.matches()) {
            throw new ValidationException("Invalid size format: '" + sizeStr + "'. Expected format: <number><unit> (e.g. 500MB)");
        }

        double value;
        try {
            value = Double.parseDouble(matcher.group(1));
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid number format in size: '" + matcher.group(1) + "'");
        }

        String unit = matcher.group(2).trim().toLowerCase();
        long multiplier = getMultiplier(unit);

        double totalBytes = value * multiplier;
        if (totalBytes > Long.MAX_VALUE) {
            throw new ValidationException("Parsed size value exceeds Maximum Long value: " + sizeStr);
        }

        return (long) totalBytes;
    }

    private static long getMultiplier(String unit) {
        return switch (unit) {
            case "", "b", "byte", "bytes" -> 1L;
            case "k", "kb", "kib" -> IEC_BASE;
            case "m", "mb", "mib" -> IEC_BASE * IEC_BASE;
            case "g", "gb", "gib" -> IEC_BASE * IEC_BASE * IEC_BASE;
            case "t", "tb", "tib" -> IEC_BASE * IEC_BASE * IEC_BASE * IEC_BASE;
            case "p", "pb", "pib" -> IEC_BASE * IEC_BASE * IEC_BASE * IEC_BASE * IEC_BASE;
            case "e", "eb", "eib" -> IEC_BASE * IEC_BASE * IEC_BASE * IEC_BASE * IEC_BASE * IEC_BASE;
            default -> throw new ValidationException("Unsupported size unit: '" + unit + "'. Supported units: B, KB, KiB, MB, MiB, GB, GiB, TB, TiB, PB, PiB, EB, EiB");
        };
    }
}
