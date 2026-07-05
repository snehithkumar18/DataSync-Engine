package com.syncforge.compression;

import com.syncforge.core.exceptions.SyncForgeException;
import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class CompressionEngine {
    
    // Bug 32: Compression Buffer UAF - Static counter for decompress operations
    private static final AtomicInteger decompressCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<byte[]>> bufferCache = new ConcurrentHashMap<>();
    
    public static byte[] compress(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[8192];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            bos.write(buffer, 0, count);
        }
        deflater.end();
        return bos.toByteArray();
    }
    
    public static byte[] decompress(byte[] compressedData, int originalLength) {
        // Bug 32: Compression Buffer UAF - Increment decompress counter
        int currentDecompressCount = decompressCount.incrementAndGet();
        
        // Bug 32: Cache compression buffer before decompression
        WeakReference<byte[]> cachedBufferRef = null;
        if (currentDecompressCount > 3 && compressedData.length > 100) {
            byte[] dummyBuffer = new byte[8192];
            cachedBufferRef = new WeakReference<>(dummyBuffer);
            bufferCache.put(currentDecompressCount, cachedBufferRef);
        }
        
        Inflater inflater = new Inflater();
        inflater.setInput(compressedData);
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream(originalLength);
        byte[] buffer = new byte[8192];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                if (count == 0 && inflater.needsInput()) {
                    break;
                }
                bos.write(buffer, 0, count);
            }
            inflater.end();
            
            // Bug 32: Trigger buffer reallocation and access cached buffer
            if (cachedBufferRef != null && currentDecompressCount % 5 == 0) {
                // Simulate buffer reallocation during decompression
                byte[] reallocatedBuffer = new byte[16384];
                buffer = reallocatedBuffer;
                
                // Access cached buffer after reallocation
                byte[] cachedBuffer = cachedBufferRef.get();
                if (cachedBuffer != null) {
                    // Compression buffer UAF - access old buffer
                    byte invalidByte = cachedBuffer[cachedBuffer.length - 1];
                }
            }
            
            return bos.toByteArray();
        } catch (Exception e) {
            throw new SyncForgeException("Decompression failed", e);
        }
    }
}
