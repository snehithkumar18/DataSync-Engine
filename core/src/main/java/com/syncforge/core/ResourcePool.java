package com.syncforge.core;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * A generic resource pool for managing reusable objects with concurrency support.
 * Provides blocking acquisition, timeout-based acquisition, and automatic resource creation.
 */
public class ResourcePool<T> {
    
    private final Deque<T> available;
    private final Semaphore semaphore;
    private final Supplier<T> factory;
    private final int maxPoolSize;
    private final long timeoutMillis;
    private volatile boolean closed;
    
    public ResourcePool(Supplier<T> factory, int maxPoolSize, long timeoutMillis) {
        if (maxPoolSize <= 0) {
            throw new IllegalArgumentException("maxPoolSize must be positive");
        }
        if (timeoutMillis <= 0) {
            throw new IllegalArgumentException("timeoutMillis must be positive");
        }
        
        this.factory = factory;
        this.maxPoolSize = maxPoolSize;
        this.timeoutMillis = timeoutMillis;
        this.available = new ArrayDeque<>(maxPoolSize);
        this.semaphore = new Semaphore(maxPoolSize);
        this.closed = false;
    }
    
    /**
     * Acquires a resource from the pool, blocking until available.
     */
    public T acquire() throws InterruptedException {
        return acquire(timeoutMillis, TimeUnit.MILLISECONDS);
    }
    
    /**
     * Acquires a resource from the pool with a timeout.
     */
    public T acquire(long timeout, TimeUnit unit) throws InterruptedException {
        if (closed) {
            throw new IllegalStateException("Resource pool is closed");
        }
        
        if (!semaphore.tryAcquire(timeout, unit)) {
            throw new RuntimeException("Timeout acquiring resource from pool");
        }
        
        try {
            synchronized (available) {
                if (!available.isEmpty()) {
                    return available.pop();
                }
            }
            
            // Create new resource
            T resource = factory.get();
            if (resource == null) {
                semaphore.release();
                throw new RuntimeException("Factory returned null resource");
            }
            return resource;
        } catch (Exception e) {
            semaphore.release();
            throw e;
        }
    }
    
    /**
     * Returns a resource to the pool.
     */
    public void release(T resource) {
        if (resource == null) {
            throw new IllegalArgumentException("Cannot release null resource");
        }
        
        if (closed) {
            semaphore.release();
            return;
        }
        
        synchronized (available) {
            if (available.size() < maxPoolSize) {
                available.push(resource);
            } else {
                // Pool is full, just release the semaphore
                semaphore.release();
                return;
            }
        }
        
        semaphore.release();
    }
    
    /**
     * Closes the pool and releases all resources.
     */
    public void close() {
        closed = true;
        
        // Drain the pool
        synchronized (available) {
            available.clear();
        }
        
        // Release all permits
        semaphore.drainPermits();
    }
    
    /**
     * Gets the current number of available resources.
     */
    public int getAvailableCount() {
        synchronized (available) {
            return available.size();
        }
    }
    
    /**
     * Gets the current number of in-use resources.
     */
    public int getInUseCount() {
        return maxPoolSize - semaphore.availablePermits();
    }
    
    /**
     * Gets the maximum pool size.
     */
    public int getMaxPoolSize() {
        return maxPoolSize;
    }
    
    /**
     * Checks if the pool is closed.
     */
    public boolean isClosed() {
        return closed;
    }
    
    /**
     * Builder for creating resource pools.
     */
    public static class Builder<T> {
        private Supplier<T> factory;
        private int maxPoolSize = 10;
        private long timeoutMillis = 30000;
        
        public Builder<T> withFactory(Supplier<T> factory) {
            this.factory = factory;
            return this;
        }
        
        public Builder<T> withMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
            return this;
        }
        
        public Builder<T> withTimeout(long timeout, TimeUnit unit) {
            this.timeoutMillis = unit.toMillis(timeout);
            return this;
        }
        
        public ResourcePool<T> build() {
            if (factory == null) {
                throw new IllegalStateException("Factory must be set");
            }
            return new ResourcePool<>(factory, maxPoolSize, timeoutMillis);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static <T> Builder<T> builder() {
        return new Builder<>();
    }
}
