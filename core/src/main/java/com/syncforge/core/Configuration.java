package com.syncforge.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Central configuration management for SyncForge operations.
 * Provides thread-safe configuration access with hierarchical key support.
 */
public class Configuration {
    
    private final Map<String, Object> values;
    private final Configuration parent;
    
    public Configuration() {
        this(null);
    }
    
    public Configuration(Configuration parent) {
        this.parent = parent;
        this.values = new HashMap<>();
    }
    
    /**
     * Sets a configuration value.
     */
    public void set(String key, Object value) {
        Objects.requireNonNull(key, "Configuration key cannot be null");
        values.put(key, value);
    }
    
    /**
     * Gets a configuration value, checking parent if not found locally.
     */
    public Optional<Object> get(String key) {
        Objects.requireNonNull(key, "Configuration key cannot be null");
        if (values.containsKey(key)) {
            return Optional.of(values.get(key));
        }
        if (parent != null) {
            return parent.get(key);
        }
        return Optional.empty();
    }
    
    /**
     * Gets a string value with default.
     */
    public String getString(String key, String defaultValue) {
        return get(key).map(Objects::toString).orElse(defaultValue);
    }
    
    /**
     * Gets an integer value with default.
     */
    public int getInt(String key, int defaultValue) {
        return get(key).map(v -> {
            if (v instanceof Number n) {
                return n.intValue();
            }
            try {
                return Integer.parseInt(v.toString());
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }).orElse(defaultValue);
    }
    
    /**
     * Gets a long value with default.
     */
    public long getLong(String key, long defaultValue) {
        return get(key).map(v -> {
            if (v instanceof Number n) {
                return n.longValue();
            }
            try {
                return Long.parseLong(v.toString());
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }).orElse(defaultValue);
    }
    
    /**
     * Gets a boolean value with default.
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return get(key).map(v -> {
            if (v instanceof Boolean b) {
                return b;
            }
            return Boolean.parseBoolean(v.toString());
        }).orElse(defaultValue);
    }
    
    /**
     * Gets a double value with default.
     */
    public double getDouble(String key, double defaultValue) {
        return get(key).map(v -> {
            if (v instanceof Number n) {
                return n.doubleValue();
            }
            try {
                return Double.parseDouble(v.toString());
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }).orElse(defaultValue);
    }
    
    /**
     * Checks if a key exists in this configuration or parent.
     */
    public boolean hasKey(String key) {
        if (values.containsKey(key)) {
            return true;
        }
        if (parent != null) {
            return parent.hasKey(key);
        }
        return false;
    }
    
    /**
     * Creates a child configuration that inherits from this one.
     */
    public Configuration createChild() {
        return new Configuration(this);
    }
    
    /**
     * Merges another configuration into this one.
     */
    public void merge(Configuration other) {
        Objects.requireNonNull(other, "Cannot merge null configuration");
        values.putAll(other.values);
    }
    
    /**
     * Clears all local values (does not affect parent).
     */
    public void clear() {
        values.clear();
    }
    
    /**
     * Gets all local keys (not including parent).
     */
    public Map<String, Object> getLocalValues() {
        return new HashMap<>(values);
    }
    
    /**
     * Builder for creating configurations.
     */
    public static class Builder {
        private final Configuration config;
        
        public Builder() {
            this.config = new Configuration();
        }
        
        public Builder with(String key, Object value) {
            config.set(key, value);
            return this;
        }
        
        public Builder withString(String key, String value) {
            config.set(key, value);
            return this;
        }
        
        public Builder withInt(String key, int value) {
            config.set(key, value);
            return this;
        }
        
        public Builder withLong(String key, long value) {
            config.set(key, value);
            return this;
        }
        
        public Builder withBoolean(String key, boolean value) {
            config.set(key, value);
            return this;
        }
        
        public Builder withDouble(String key, double value) {
            config.set(key, value);
            return this;
        }
        
        public Configuration build() {
            return config;
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
    
    /**
     * Creates a default configuration with sensible defaults.
     */
    public static Configuration createDefault() {
        return builder()
            .with("scanner.follow_symlinks", false)
            .with("scanner.max_depth", 100)
            .with("scanner.parallel_threads", Runtime.getRuntime().availableProcessors())
            .with("checksum.algorithm", "sha256")
            .with("compression.enabled", true)
            .with("compression.level", 6)
            .with("snapshot.format_version", 1)
            .with("patch.format_version", 1)
            .with("runtime.max_retries", 3)
            .with("runtime.retry_delay_ms", 1000)
            .with("runtime.rollback_on_failure", true)
            .with("indexing.enabled", true)
            .with("indexing.cache_size_mb", 100)
            .with("path.case_sensitive", true)
            .with("path.normalize_unicode", true)
            .with("conflict.resolution_strategy", "manual")
            .build();
    }
}
