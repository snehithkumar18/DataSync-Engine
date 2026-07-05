package com.syncforge.conflict;

import java.util.HashMap;
import java.util.Map;

/**
 * Context information for conflict resolution.
 * Provides additional metadata and configuration for resolution strategies.
 */
public class ResolutionContext {
    
    private final Map<String, Object> properties;
    private final ResolutionStrategy defaultStrategy;
    private final boolean allowBackups;
    private final boolean preserveTimestamps;
    private final boolean preservePermissions;
    
    private ResolutionContext(Builder builder) {
        this.properties = new HashMap<>(builder.properties);
        this.defaultStrategy = builder.defaultStrategy;
        this.allowBackups = builder.allowBackups;
        this.preserveTimestamps = builder.preserveTimestamps;
        this.preservePermissions = builder.preservePermissions;
    }
    
    /**
     * Gets a property value.
     */
    @SuppressWarnings("unchecked")
    public <T> T getProperty(String key, Class<T> type) {
        Object value = properties.get(key);
        if (value != null && type.isInstance(value)) {
            return (T) value;
        }
        return null;
    }
    
    /**
     * Gets a property value with a default.
     */
    @SuppressWarnings("unchecked")
    public <T> T getProperty(String key, Class<T> type, T defaultValue) {
        T value = getProperty(key, type);
        return value != null ? value : defaultValue;
    }
    
    /**
     * Sets a property value.
     */
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }
    
    /**
     * Gets the default resolution strategy.
     */
    public ResolutionStrategy getDefaultStrategy() {
        return defaultStrategy;
    }
    
    /**
     * Checks if backups are allowed.
     */
    public boolean isAllowBackups() {
        return allowBackups;
    }
    
    /**
     * Checks if timestamps should be preserved.
     */
    public boolean isPreserveTimestamps() {
        return preserveTimestamps;
    }
    
    /**
     * Checks if permissions should be preserved.
     */
    public boolean isPreservePermissions() {
        return preservePermissions;
    }
    
    /**
     * Gets all properties.
     */
    public Map<String, Object> getProperties() {
        return new HashMap<>(properties);
    }
    
    /**
     * Builder for creating resolution contexts.
     */
    public static class Builder {
        private final Map<String, Object> properties = new HashMap<>();
        private ResolutionStrategy defaultStrategy = ResolutionStrategy.LATEST_WINS;
        private boolean allowBackups = true;
        private boolean preserveTimestamps = true;
        private boolean preservePermissions = true;
        
        public Builder withDefaultStrategy(ResolutionStrategy strategy) {
            this.defaultStrategy = strategy;
            return this;
        }
        
        public Builder withAllowBackups(boolean allowBackups) {
            this.allowBackups = allowBackups;
            return this;
        }
        
        public Builder withPreserveTimestamps(boolean preserveTimestamps) {
            this.preserveTimestamps = preserveTimestamps;
            return this;
        }
        
        public Builder withPreservePermissions(boolean preservePermissions) {
            this.preservePermissions = preservePermissions;
            return this;
        }
        
        public Builder withProperty(String key, Object value) {
            this.properties.put(key, value);
            return this;
        }
        
        public Builder withProperties(Map<String, Object> properties) {
            this.properties.putAll(properties);
            return this;
        }
        
        public ResolutionContext build() {
            return new ResolutionContext(this);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
    
    /**
     * Creates a default resolution context.
     */
    public static ResolutionContext createDefault() {
        return builder().build();
    }
}
