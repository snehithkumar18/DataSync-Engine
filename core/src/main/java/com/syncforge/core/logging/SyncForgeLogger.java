package com.syncforge.core.logging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * SyncForgeLogger provides high-integrity logging with multi-level support,
 * ThreadLocal Mapped Diagnostic Context (MDC), and built-in console and
 * rotating file appenders.
 * 
 * <p>It is designed to be thread-safe and lightweight, ensuring file writes and
 * rotations are executed atomically. Inputs are validated at all method entry points.</p>
 */
public class SyncForgeLogger {

    /**
     * Logging levels.
     */
    public enum Level {
        TRACE(0),
        DEBUG(1),
        INFO(2),
        WARN(3),
        ERROR(4);

        private final int severity;

        Level(int severity) {
            this.severity = severity;
        }

        public boolean isGreaterOrEqual(Level other) {
            return this.severity >= other.severity;
        }
    }

    // ThreadLocal Mapped Diagnostic Context (MDC)
    private static final ThreadLocal<Map<String, String>> diagnosticContext = 
            ThreadLocal.withInitial(HashMap::new);

    private static Level globalLevel = Level.INFO;
    private static boolean consoleEnabled = true;
    
    // Rotating file appender state
    private static String logFilePath = null;
    private static long maxFileSizeBytes = 5 * 1024 * 1024; // Default: 5MB
    private static int maxBackupIndex = 5; // Default: 5 files
    private static PrintWriter fileWriter = null;
    private static final Object fileLock = new Object();

    private final String loggerName;
    private static final DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * Constructs a new SyncForgeLogger for the specified class.
     *
     * @param clazz the class for which to create the logger. Must not be null.
     * @throws NullPointerException if {@code clazz} is null.
     */
    public SyncForgeLogger(Class<?> clazz) {
        Objects.requireNonNull(clazz, "Logger class must not be null");
        this.loggerName = clazz.getName();
    }

    /**
     * Constructs a new SyncForgeLogger with the specified name.
     *
     * @param name the name of the logger. Must not be null or blank.
     * @throws NullPointerException if {@code name} is null.
     * @throws IllegalArgumentException if {@code name} is blank.
     */
    public SyncForgeLogger(String name) {
        Objects.requireNonNull(name, "Logger name must not be null");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Logger name must not be empty or blank");
        }
        this.loggerName = name;
    }

    /**
     * Sets the global logging level. Messages below this level will be discarded.
     *
     * @param level the log level. Must not be null.
     * @throws NullPointerException if {@code level} is null.
     */
    public static void setLogLevel(Level level) {
        globalLevel = Objects.requireNonNull(level, "Log level must not be null");
    }

    /**
     * Returns the current global logging level.
     *
     * @return the active global {@link Level}.
     */
    public static Level getLogLevel() {
        return globalLevel;
    }

    /**
     * Configures the console logging output.
     *
     * @param enabled true to print to standard console outputs, false to disable.
     */
    public static void setConsoleEnabled(boolean enabled) {
        consoleEnabled = enabled;
    }

    /**
     * Configures the rotating file appender. If already configured, it closes the previous writer
     * and opens a new file handle at the specified path.
     *
     * @param filePath       the absolute or relative path to the log file. Must not be null.
     * @param maxSizeBytes   the maximum size of the file before rotation (e.g. 5MB). Must be &gt; 0.
     * @param maxBackups     the maximum number of backup files to keep. Must be &gt;= 0.
     * @throws NullPointerException     if {@code filePath} is null.
     * @throws IllegalArgumentException if {@code maxSizeBytes} is &lt;= 0 or {@code maxBackups} is &lt; 0.
     */
    public static void configureFileAppender(String filePath, long maxSizeBytes, int maxBackups) {
        Objects.requireNonNull(filePath, "Log file path must not be null");
        if (maxSizeBytes <= 0) {
            throw new IllegalArgumentException("Max file size must be greater than 0");
        }
        if (maxBackups < 0) {
            throw new IllegalArgumentException("Max backup index must be >= 0");
        }

        synchronized (fileLock) {
            closeFileWriter();
            logFilePath = filePath.replace('\\', '/');
            maxFileSizeBytes = maxSizeBytes;
            maxBackupIndex = maxBackups;
            try {
                openFileWriter();
            } catch (IOException e) {
                System.err.printf("[SyncForgeLogger] Failed to initialize file appender for path '%s': %s%n", 
                        logFilePath, e.getMessage());
            }
        }
    }

    private static void openFileWriter() throws IOException {
        File file = new File(logFilePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        fileWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8), true);
    }

    private static void closeFileWriter() {
        if (fileWriter != null) {
            fileWriter.close();
            fileWriter = null;
        }
    }

    /**
     * Adds a key-value diagnostic property to the thread's diagnostic context (MDC).
     *
     * @param key   the context key. Must not be null or blank.
     * @param value the context value. Must not be null.
     * @throws NullPointerException     if key or value is null.
     * @throws IllegalArgumentException if key is blank.
     */
    public static void putContext(String key, String value) {
        Objects.requireNonNull(key, "MDC key must not be null");
        Objects.requireNonNull(value, "MDC value must not be null");
        if (key.isBlank()) {
            throw new IllegalArgumentException("MDC key must not be blank");
        }
        diagnosticContext.get().put(key, value);
    }

    /**
     * Retrieves a key's value from the diagnostic context.
     *
     * @param key the context key. Must not be null.
     * @return the associated value, or null if not found.
     * @throws NullPointerException if key is null.
     */
    public static String getContext(String key) {
        Objects.requireNonNull(key, "MDC key must not be null");
        return diagnosticContext.get().get(key);
    }

    /**
     * Removes a diagnostic property from the thread's diagnostic context.
     *
     * @param key the context key to remove. Must not be null.
     * @throws NullPointerException if key is null.
     */
    public static void removeContext(String key) {
        Objects.requireNonNull(key, "MDC key must not be null");
        diagnosticContext.get().remove(key);
    }

    /**
     * Clears all diagnostic properties from the current thread's context.
     */
    public static void clearContext() {
        diagnosticContext.get().clear();
    }

    /**
     * Returns an unmodifiable map of the current thread's diagnostic context.
     *
     * @return map representing current thread context properties.
     */
    public static Map<String, String> getContextMap() {
        return Collections.unmodifiableMap(diagnosticContext.get());
    }

    /**
     * Log a trace message.
     *
     * @param format the message format string. Must not be null.
     * @param args   arguments referenced by the format string.
     */
    public void trace(String format, Object... args) {
        log(Level.TRACE, format, null, args);
    }

    /**
     * Log a debug message.
     *
     * @param format the message format string. Must not be null.
     * @param args   arguments referenced by the format string.
     */
    public void debug(String format, Object... args) {
        log(Level.DEBUG, format, null, args);
    }

    /**
     * Log an info message.
     *
     * @param format the message format string. Must not be null.
     * @param args   arguments referenced by the format string.
     */
    public void info(String format, Object... args) {
        log(Level.INFO, format, null, args);
    }

    /**
     * Log a warning message.
     *
     * @param format the message format string. Must not be null.
     * @param args   arguments referenced by the format string.
     */
    public void warn(String format, Object... args) {
        log(Level.WARN, format, null, args);
    }

    /**
     * Log a warning message with an underlying throwable.
     *
     * @param throwable the cause of the warning. Must not be null.
     * @param format    the message format string. Must not be null.
     * @param args      arguments referenced by the format string.
     */
    public void warn(Throwable throwable, String format, Object... args) {
        log(Level.WARN, format, Objects.requireNonNull(throwable, "Throwable must not be null"), args);
    }

    /**
     * Log an error message.
     *
     * @param format the message format string. Must not be null.
     * @param args   arguments referenced by the format string.
     */
    public void error(String format, Object... args) {
        log(Level.ERROR, format, null, args);
    }

    /**
     * Log an error message with an underlying throwable.
     *
     * @param throwable the cause of the error. Must not be null.
     * @param format    the message format string. Must not be null.
     * @param args      arguments referenced by the format string.
     */
    public void error(Throwable throwable, String format, Object... args) {
        log(Level.ERROR, format, Objects.requireNonNull(throwable, "Throwable must not be null"), args);
    }

    private void log(Level level, String format, Throwable throwable, Object... args) {
        Objects.requireNonNull(format, "Log format string must not be null");
        if (level.isGreaterOrEqual(globalLevel)) {
            String formattedMessage = String.format(format, args);
            String timestamp = LocalDateTime.now().format(timestampFormatter);
            String threadName = Thread.currentThread().getName();
            
            // Build diagnostic context string
            Map<String, String> mdc = diagnosticContext.get();
            String mdcStr = "";
            if (!mdc.isEmpty()) {
                StringBuilder mdcSb = new StringBuilder(" [");
                mdc.forEach((k, v) -> mdcSb.append(k).append("=").append(v).append(" "));
                mdcSb.setLength(mdcSb.length() - 1);
                mdcSb.append("]");
                mdcStr = mdcSb.toString();
            }

            // Assemble complete log line
            StringBuilder logLine = new StringBuilder()
                    .append(timestamp)
                    .append(" [")
                    .append(threadName)
                    .append("] ")
                    .append(String.format("%-5s", level.name()))
                    .append(" ")
                    .append(loggerName)
                    .append(mdcStr)
                    .append(" - ")
                    .append(formattedMessage);

            if (throwable != null) {
                logLine.append(System.lineSeparator());
                appendThrowableDetails(logLine, throwable);
            }

            String messageToWrite = logLine.toString();

            // Console output
            if (consoleEnabled) {
                if (level == Level.ERROR) {
                    System.err.println(messageToWrite);
                } else {
                    System.out.println(messageToWrite);
                }
            }

            // File output
            if (logFilePath != null) {
                writeLogToFile(messageToWrite);
            }
        }
    }

    private void appendThrowableDetails(StringBuilder sb, Throwable t) {
        sb.append(t.getClass().getName()).append(": ").append(t.getMessage()).append(System.lineSeparator());
        for (StackTraceElement element : t.getStackTrace()) {
            sb.append("\tat ").append(element.toString()).append(System.lineSeparator());
        }
        Throwable cause = t.getCause();
        if (cause != null) {
            sb.append("Caused by: ");
            appendThrowableDetails(sb, cause);
        }
    }

    private void writeLogToFile(String logLine) {
        synchronized (fileLock) {
            if (fileWriter != null) {
                fileWriter.println(logLine);
                fileWriter.flush();
                checkAndRotateFile();
            }
        }
    }

    private void checkAndRotateFile() {
        File file = new File(logFilePath);
        if (file.exists() && file.length() >= maxFileSizeBytes) {
            closeFileWriter();
            rotate(0);
            try {
                openFileWriter();
            } catch (IOException e) {
                System.err.printf("[SyncForgeLogger] Failed to reopen log file after rotation: %s%n", e.getMessage());
            }
        }
    }

    private void rotate(int index) {
        if (index >= maxBackupIndex) {
            File target = new File(logFilePath + "." + index);
            if (target.exists()) {
                target.delete();
            }
            return;
        }

        String sourcePath = index == 0 ? logFilePath : logFilePath + "." + index;
        File source = new File(sourcePath);
        if (source.exists()) {
            // Recursively rotate next levels first
            rotate(index + 1);
            File dest = new File(logFilePath + "." + (index + 1));
            source.renameTo(dest);
        }
    }
}
