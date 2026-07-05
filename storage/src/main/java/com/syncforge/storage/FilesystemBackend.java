package com.syncforge.storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Filesystem-based storage backend.
 * Stores data as files in a directory structure.
 */
public class FilesystemBackend implements StorageBackend {
    
    private final Path basePath;
    private final boolean createDirectories;
    
    public FilesystemBackend(String basePath) {
        this(basePath, true);
    }
    
    public FilesystemBackend(String basePath, boolean createDirectories) {
        this.basePath = Paths.get(basePath);
        this.createDirectories = createDirectories;
        
        if (createDirectories && !Files.exists(this.basePath)) {
            try {
                Files.createDirectories(this.basePath);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create base directory: " + basePath, e);
            }
        }
    }
    
    @Override
    public void store(String key, byte[] data) throws IOException {
        Path filePath = resolvePath(key);
        Path parentDir = filePath.getParent();
        
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }
        
        Files.write(filePath, data);
    }
    
    @Override
    public void store(String key, InputStream data) throws IOException {
        Path filePath = resolvePath(key);
        Path parentDir = filePath.getParent();
        
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }
        
        Files.copy(data, filePath, StandardCopyOption.REPLACE_EXISTING);
    }
    
    @Override
    public byte[] retrieve(String key) throws IOException {
        Path filePath = resolvePath(key);
        
        if (!Files.exists(filePath)) {
            return null;
        }
        
        return Files.readAllBytes(filePath);
    }
    
    @Override
    public InputStream retrieveAsStream(String key) throws IOException {
        Path filePath = resolvePath(key);
        
        if (!Files.exists(filePath)) {
            return null;
        }
        
        return new FileInputStream(filePath.toFile());
    }
    
    @Override
    public boolean exists(String key) throws IOException {
        Path filePath = resolvePath(key);
        return Files.exists(filePath);
    }
    
    @Override
    public void delete(String key) throws IOException {
        Path filePath = resolvePath(key);
        
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
    }
    
    @Override
    public List<String> listKeys() throws IOException {
        return listKeys("");
    }
    
    @Override
    public List<String> listKeys(String prefix) throws IOException {
        Path prefixPath = resolvePath(prefix);
        List<String> keys = new java.util.ArrayList<>();
        
        if (!Files.exists(prefixPath)) {
            return keys;
        }
        
        try (Stream<Path> stream = Files.walk(prefixPath)) {
            stream.filter(Files::isRegularFile)
                 .forEach(path -> {
                     String relativePath = basePath.relativize(path).toString();
                     keys.add(relativePath.replace(File.separatorChar, '/'));
                 });
        }
        
        return keys;
    }
    
    @Override
    public Map<String, String> getMetadata(String key) throws IOException {
        Path filePath = resolvePath(key);
        Map<String, String> metadata = new HashMap<>();
        
        if (!Files.exists(filePath)) {
            return metadata;
        }
        
        metadata.put("size", String.valueOf(Files.size(filePath)));
        metadata.put("lastModified", String.valueOf(Files.getLastModifiedTime(filePath).toMillis()));
        metadata.put("isRegularFile", String.valueOf(Files.isRegularFile(filePath)));
        
        return metadata;
    }
    
    @Override
    public void setMetadata(String key, Map<String, String> metadata) throws IOException {
        // Filesystem backend doesn't support arbitrary metadata
        // Could be implemented using extended attributes or sidecar files
    }
    
    @Override
    public long getSize(String key) throws IOException {
        Path filePath = resolvePath(key);
        
        if (!Files.exists(filePath)) {
            return -1;
        }
        
        return Files.size(filePath);
    }
    
    @Override
    public void clear() throws IOException {
        if (Files.exists(basePath)) {
            try (Stream<Path> stream = Files.walk(basePath)) {
                stream.sorted((a, b) -> -a.compareTo(b)) // Delete files before directories
                     .forEach(path -> {
                         try {
                             Files.delete(path);
                         } catch (IOException e) {
                             // Ignore errors during clear
                         }
                     });
            }
        }
    }
    
    @Override
    public void close() throws IOException {
        // No resources to release for filesystem backend
    }
    
    @Override
    public String getBackendType() {
        return "filesystem";
    }
    
    @Override
    public boolean isAvailable() {
        return Files.exists(basePath) && Files.isDirectory(basePath);
    }
    
    /**
     * Resolves a key to a filesystem path.
     */
    private Path resolvePath(String key) {
        // Sanitize key to prevent path traversal
        String sanitizedKey = key.replace("..", "").replace("\\", "/");
        return basePath.resolve(sanitizedKey.replace("/", File.separator));
    }
    
    /**
     * Gets the base path.
     */
    public Path getBasePath() {
        return basePath;
    }
    
    /**
     * Builder for creating filesystem backends.
     */
    public static class Builder {
        private String basePath;
        private boolean createDirectories = true;
        
        public Builder withBasePath(String basePath) {
            this.basePath = basePath;
            return this;
        }
        
        public Builder withCreateDirectories(boolean createDirectories) {
            this.createDirectories = createDirectories;
            return this;
        }
        
        public FilesystemBackend build() {
            if (basePath == null) {
                throw new IllegalStateException("Base path is required");
            }
            return new FilesystemBackend(basePath, createDirectories);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}
