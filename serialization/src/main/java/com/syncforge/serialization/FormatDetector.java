package com.syncforge.serialization;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Detects the format of serialized data.
 * Identifies whether data is in binary, JSON, or XML format.
 */
public class FormatDetector {
    
    /**
     * Detects the format of a file.
     */
    public SerializationFormat detectFormat(Path path) throws IOException {
        byte[] header = readHeader(path, 16);
        return detectFormat(header, path.toString());
    }
    
    /**
     * Detects the format from byte array.
     */
    public SerializationFormat detectFormat(byte[] data, String filename) {
        if (data == null || data.length == 0) {
            return SerializationFormat.UNKNOWN;
        }
        
        // Check for binary signature
        if (isBinaryFormat(data)) {
            return SerializationFormat.BINARY;
        }
        
        // Check for JSON
        if (isJsonFormat(data)) {
            return SerializationFormat.JSON;
        }
        
        // Check for XML
        if (isXmlFormat(data)) {
            return SerializationFormat.XML;
        }
        
        // Check file extension
        String lowerName = filename.toLowerCase();
        if (lowerName.endsWith(".bin") || lowerName.endsWith(".dat")) {
            return SerializationFormat.BINARY;
        } else if (lowerName.endsWith(".json")) {
            return SerializationFormat.JSON;
        } else if (lowerName.endsWith(".xml")) {
            return SerializationFormat.XML;
        }
        
        return SerializationFormat.UNKNOWN;
    }
    
    /**
     * Detects the format from a string.
     */
    public SerializationFormat detectFormat(String data) {
        if (data == null || data.isEmpty()) {
            return SerializationFormat.UNKNOWN;
        }
        
        String trimmed = data.trim();
        
        if (isJsonString(trimmed)) {
            return SerializationFormat.JSON;
        }
        
        if (isXmlString(trimmed)) {
            return SerializationFormat.XML;
        }
        
        return SerializationFormat.UNKNOWN;
    }
    
    /**
     * Checks if data is in binary format.
     */
    private boolean isBinaryFormat(byte[] data) {
        // Check for binary signature (SyncForge binary format)
        if (data.length >= 4) {
            // SyncForge binary signature: 0x53 0x46 0x42 0x31 ("SFB1")
            if (data[0] == 0x53 && data[1] == 0x46 && data[2] == 0x42 && data[3] == 0x31) {
                return true;
            }
        }
        
        // Check for non-printable characters (heuristic for binary)
        int nonPrintableCount = 0;
        for (int i = 0; i < Math.min(data.length, 100); i++) {
            byte b = data[i];
            if (b < 32 && b != '\t' && b != '\n' && b != '\r') {
                nonPrintableCount++;
            }
        }
        
        // If more than 30% of first 100 bytes are non-printable, assume binary
        return nonPrintableCount > 30;
    }
    
    /**
     * Checks if data is in JSON format.
     */
    private boolean isJsonFormat(byte[] data) {
        String str = new String(data, 0, Math.min(data.length, 100));
        return isJsonString(str.trim());
    }
    
    /**
     * Checks if string is JSON.
     */
    private boolean isJsonString(String str) {
        str = str.trim();
        return str.startsWith("{") || str.startsWith("[");
    }
    
    /**
     * Checks if data is in XML format.
     */
    private boolean isXmlFormat(byte[] data) {
        String str = new String(data, 0, Math.min(data.length, 100));
        return isXmlString(str.trim());
    }
    
    /**
     * Checks if string is XML.
     */
    private boolean isXmlString(String str) {
        str = str.trim();
        return str.startsWith("<") && str.endsWith(">");
    }
    
    /**
     * Reads the header bytes from a file.
     */
    private byte[] readHeader(Path path, int bytes) throws IOException {
        if (!Files.exists(path) || Files.size(path) == 0) {
            return new byte[0];
        }
        
        byte[] data = new byte[Math.min((int) Files.size(path), bytes)];
        try (java.io.InputStream is = Files.newInputStream(path)) {
            is.read(data);
        }
        
        return data;
    }
    
    /**
     * Serialization format enumeration.
     */
    public enum SerializationFormat {
        BINARY,
        JSON,
        XML,
        UNKNOWN
    }
    
    /**
     * Creates a new format detector.
     */
    public static FormatDetector create() {
        return new FormatDetector();
    }
}
