package com.syncforge.core;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * Tracks progress of long-running operations with detailed metrics.
 * Thread-safe implementation for concurrent progress updates.
 */
public class ProgressTracker {
    
    private final String operationName;
    private final long totalItems;
    private final AtomicLong processedItems;
    private final AtomicLong failedItems;
    private final Instant startTime;
    private Instant endTime;
    private final List<ProgressListener> listeners;
    private final List<String> warnings;
    private final Object lock = new Object();
    
    public ProgressTracker(String operationName, long totalItems) {
        this.operationName = operationName;
        this.totalItems = totalItems;
        this.processedItems = new AtomicLong(0);
        this.failedItems = new AtomicLong(0);
        this.startTime = Instant.now();
        this.listeners = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }
    
    /**
     * Increments the processed item count.
     */
    public void incrementProcessed() {
        long current = processedItems.incrementAndGet();
        notifyListeners(current, failedItems.get());
    }
    
    /**
     * Increments the processed item count by a specific amount.
     */
    public void incrementProcessed(long amount) {
        long current = processedItems.addAndGet(amount);
        notifyListeners(current, failedItems.get());
    }
    
    /**
     * Increments the failed item count.
     */
    public void incrementFailed() {
        long current = failedItems.incrementAndGet();
        notifyListeners(processedItems.get(), current);
    }
    
    /**
     * Increments the failed item count by a specific amount.
     */
    public void incrementFailed(long amount) {
        long current = failedItems.addAndGet(amount);
        notifyListeners(processedItems.get(), current);
    }
    
    /**
     * Marks the operation as complete.
     */
    public void complete() {
        this.endTime = Instant.now();
        notifyCompletion();
    }
    
    /**
     * Adds a warning message.
     */
    public void addWarning(String warning) {
        synchronized (lock) {
            warnings.add(warning);
        }
    }
    
    /**
     * Adds a progress listener.
     */
    public void addListener(ProgressListener listener) {
        synchronized (lock) {
            listeners.add(listener);
        }
    }
    
    /**
     * Removes a progress listener.
     */
    public void removeListener(ProgressListener listener) {
        synchronized (lock) {
            listeners.remove(listener);
        }
    }
    
    /**
     * Gets the current progress percentage (0-100).
     */
    public double getProgressPercentage() {
        if (totalItems <= 0) {
            return 100.0;
        }
        return (processedItems.get() * 100.0) / totalItems;
    }
    
    /**
     * Gets the number of processed items.
     */
    public long getProcessedItems() {
        return processedItems.get();
    }
    
    /**
     * Gets the number of failed items.
     */
    public long getFailedItems() {
        return failedItems.get();
    }
    
    /**
     * Gets the total number of items.
     */
    public long getTotalItems() {
        return totalItems;
    }
    
    /**
     * Gets the number of remaining items.
     */
    public long getRemainingItems() {
        return Math.max(0, totalItems - processedItems.get());
    }
    
    /**
     * Gets the elapsed time since start.
     */
    public Duration getElapsedTime() {
        Instant end = endTime != null ? endTime : Instant.now();
        return Duration.between(startTime, end);
    }
    
    /**
     * Estimates the remaining time based on current progress.
     */
    public Duration getEstimatedRemainingTime() {
        long processed = processedItems.get();
        if (processed <= 0) {
            return Duration.ZERO;
        }
        
        Duration elapsed = getElapsedTime();
        long remaining = getRemainingItems();
        double rate = (double) processed / elapsed.toMillis();
        
        if (rate <= 0) {
            return Duration.ZERO;
        }
        
        long remainingMs = (long) (remaining / rate);
        return Duration.ofMillis(remainingMs);
    }
    
    /**
     * Gets the operation name.
     */
    public String getOperationName() {
        return operationName;
    }
    
    /**
     * Gets the start time.
     */
    public Instant getStartTime() {
        return startTime;
    }
    
    /**
     * Gets the end time (null if not complete).
     */
    public Instant getEndTime() {
        return endTime;
    }
    
    /**
     * Checks if the operation is complete.
     */
    public boolean isComplete() {
        return endTime != null;
    }
    
    /**
     * Gets all warnings.
     */
    public List<String> getWarnings() {
        synchronized (lock) {
            return new ArrayList<>(warnings);
        }
    }
    
    /**
     * Creates a progress snapshot.
     */
    public ProgressSnapshot getSnapshot() {
        return new ProgressSnapshot(
            operationName,
            totalItems,
            processedItems.get(),
            failedItems.get(),
            getProgressPercentage(),
            getElapsedTime(),
            getEstimatedRemainingTime(),
            isComplete(),
            getWarnings()
        );
    }
    
    private void notifyListeners(long processed, long failed) {
        List<ProgressListener> copy;
        synchronized (lock) {
            copy = new ArrayList<>(listeners);
        }
        
        for (ProgressListener listener : copy) {
            try {
                listener.onProgress(processed, failed, totalItems, getProgressPercentage());
            } catch (Exception e) {
                // Don't let listener errors break tracking
            }
        }
    }
    
    private void notifyCompletion() {
        List<ProgressListener> copy;
        synchronized (lock) {
            copy = new ArrayList<>(listeners);
        }
        
        for (ProgressListener listener : copy) {
            try {
                listener.onComplete(getSnapshot());
            } catch (Exception e) {
                // Don't let listener errors break tracking
            }
        }
    }
    
    /**
     * Listener interface for progress updates.
     */
    public interface ProgressListener {
        void onProgress(long processed, long failed, long total, double percentage);
        void onComplete(ProgressSnapshot snapshot);
    }
    
    /**
     * Immutable snapshot of progress state.
     */
    public record ProgressSnapshot(
        String operationName,
        long totalItems,
        long processedItems,
        long failedItems,
        double progressPercentage,
        Duration elapsedTime,
        Duration estimatedRemainingTime,
        boolean complete,
        List<String> warnings
    ) {
        public ProgressSnapshot {
            if (warnings == null) {
                warnings = List.of();
            } else {
                warnings = List.copyOf(warnings);
            }
        }
        
        public double getProcessingRate() {
            if (elapsedTime.toMillis() <= 0) {
                return 0.0;
            }
            return (processedItems * 1000.0) / elapsedTime.toMillis();
        }
        
        public double getFailureRate() {
            if (elapsedTime.toMillis() <= 0) {
                return 0.0;
            }
            return (failedItems * 1000.0) / elapsedTime.toMillis();
        }
    }
    
    /**
     * Builder for creating progress trackers with listeners.
     */
    public static class Builder {
        private final String operationName;
        private final long totalItems;
        private final List<ProgressListener> listeners = new ArrayList<>();
        
        public Builder(String operationName, long totalItems) {
            this.operationName = operationName;
            this.totalItems = totalItems;
        }
        
        public Builder withListener(ProgressListener listener) {
            listeners.add(listener);
            return this;
        }
        
        public Builder withListener(Consumer<ProgressSnapshot> onComplete) {
            listeners.add(new ProgressListener() {
                @Override
                public void onProgress(long processed, long failed, long total, double percentage) {
                    // Ignore progress updates
                }
                
                @Override
                public void onComplete(ProgressSnapshot snapshot) {
                    onComplete.accept(snapshot);
                }
            });
            return this;
        }
        
        public ProgressTracker build() {
            ProgressTracker tracker = new ProgressTracker(operationName, totalItems);
            for (ProgressListener listener : listeners) {
                tracker.addListener(listener);
            }
            return tracker;
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder(String operationName, long totalItems) {
        return new Builder(operationName, totalItems);
    }
}
