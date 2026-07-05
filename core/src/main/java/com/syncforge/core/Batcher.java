package com.syncforge.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Batches items for efficient processing.
 * Accumulates items and processes them when the batch size is reached or on flush.
 */
public class Batcher<T> {
    
    private final int batchSize;
    private final Consumer<List<T>> batchProcessor;
    private final List<T> currentBatch;
    private final Object lock = new Object();
    private volatile boolean closed;
    private long totalProcessed;
    private long totalBatches;
    
    public Batcher(int batchSize, Consumer<List<T>> batchProcessor) {
        if (batchSize <= 0) {
            throw new IllegalArgumentException("batchSize must be positive");
        }
        
        this.batchSize = batchSize;
        this.batchProcessor = batchProcessor;
        this.currentBatch = new ArrayList<>(batchSize);
        this.closed = false;
    }
    
    /**
     * Adds an item to the batch, processing if the batch is full.
     */
    public void add(T item) {
        if (closed) {
            throw new IllegalStateException("Batcher is closed");
        }
        
        synchronized (lock) {
            currentBatch.add(item);
            
            if (currentBatch.size() >= batchSize) {
                processBatch();
            }
        }
    }
    
    /**
     * Adds multiple items to the batch.
     */
    public void addAll(List<T> items) {
        if (closed) {
            throw new IllegalStateException("Batcher is closed");
        }
        
        synchronized (lock) {
            for (T item : items) {
                currentBatch.add(item);
                
                if (currentBatch.size() >= batchSize) {
                    processBatch();
                }
            }
        }
    }
    
    /**
     * Flushes the current batch, even if not full.
     */
    public void flush() {
        synchronized (lock) {
            if (!currentBatch.isEmpty()) {
                processBatch();
            }
        }
    }
    
    /**
     * Closes the batcher and flushes remaining items.
     */
    public void close() {
        synchronized (lock) {
            closed = true;
            if (!currentBatch.isEmpty()) {
                processBatch();
            }
        }
    }
    
    /**
     * Gets the current batch size.
     */
    public int getCurrentBatchSize() {
        synchronized (lock) {
            return currentBatch.size();
        }
    }
    
    /**
     * Gets the configured batch size.
     */
    public int getBatchSize() {
        return batchSize;
    }
    
    /**
     * Gets the total number of items processed.
     */
    public long getTotalProcessed() {
        return totalProcessed;
    }
    
    /**
     * Gets the total number of batches processed.
     */
    public long getTotalBatches() {
        return totalBatches;
    }
    
    /**
     * Checks if the batcher is closed.
     */
    public boolean isClosed() {
        return closed;
    }
    
    private void processBatch() {
        if (currentBatch.isEmpty()) {
            return;
        }
        
        List<T> batchToProcess = new ArrayList<>(currentBatch);
        currentBatch.clear();
        
        try {
            batchProcessor.accept(batchToProcess);
            totalProcessed += batchToProcess.size();
            totalBatches++;
        } catch (Exception e) {
            // Re-add items to current batch for retry
            currentBatch.addAll(0, batchToProcess);
            throw new RuntimeException("Batch processing failed", e);
        }
    }
    
    /**
     * Builder for creating batchers.
     */
    public static class Builder<T> {
        private int batchSize = 100;
        private Consumer<List<T>> batchProcessor;
        
        public Builder<T> withBatchSize(int batchSize) {
            this.batchSize = batchSize;
            return this;
        }
        
        public Builder<T> withBatchProcessor(Consumer<List<T>> batchProcessor) {
            this.batchProcessor = batchProcessor;
            return this;
        }
        
        public Batcher<T> build() {
            if (batchProcessor == null) {
                throw new IllegalStateException("Batch processor must be set");
            }
            return new Batcher<>(batchSize, batchProcessor);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static <T> Builder<T> builder() {
        return new Builder<>();
    }
}
