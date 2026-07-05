package com.syncforge.snapshot;

import com.syncforge.compression.CompressionEngine;

import java.io.IOException;

/**
 * Handles compression and decompression of snapshot data.
 * Supports multiple compression algorithms and compression levels.
 */
public class SnapshotCompressor {
    
    private final CompressionEngine compressionEngine;
    private final int compressionLevel;
    
    public SnapshotCompressor() {
        this(new CompressionEngine(), 6);
    }
    
    public SnapshotCompressor(CompressionEngine compressionEngine, int compressionLevel) {
        this.compressionEngine = compressionEngine;
        this.compressionLevel = Math.max(0, Math.min(9, compressionLevel));
    }
    
    /**
     * Compresses snapshot data.
     */
    public byte[] compress(byte[] data) throws IOException {
        if (data == null || data.length == 0) {
            return new byte[0];
        }
        
        return CompressionEngine.compress(data);
    }
    
    /**
     * Decompresses snapshot data.
     */
    public byte[] decompress(byte[] compressedData) throws IOException {
        if (compressedData == null || compressedData.length == 0) {
            return new byte[0];
        }
        
        // Estimate original length (heuristic: 4x compressed size)
        int estimatedLength = compressedData.length * 4;
        return CompressionEngine.decompress(compressedData, estimatedLength);
    }
    
    /**
     * Estimates the compression ratio.
     */
    public double estimateCompressionRatio(byte[] data) throws IOException {
        if (data == null || data.length == 0) {
            return 0.0;
        }
        
        byte[] compressed = compress(data);
        return (double) compressed.length / data.length;
    }
    
    /**
     * Gets the compression level.
     */
    public int getCompressionLevel() {
        return compressionLevel;
    }
    
    /**
     * Builder for creating snapshot compressors.
     */
    public static class Builder {
        private CompressionEngine compressionEngine = new CompressionEngine();
        private int compressionLevel = 6;
        
        public Builder withCompressionEngine(CompressionEngine compressionEngine) {
            this.compressionEngine = compressionEngine;
            return this;
        }
        
        public Builder withCompressionLevel(int compressionLevel) {
            this.compressionLevel = compressionLevel;
            return this;
        }
        
        public SnapshotCompressor build() {
            return new SnapshotCompressor(compressionEngine, compressionLevel);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}
