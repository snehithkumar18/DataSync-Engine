package com.syncforge.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.syncforge.metadata.EntryMetadata;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Map;

/**
 * JSON serializer for SyncForge data models.
 * Provides human-readable serialization for manifests, snapshots, and patches.
 */
public class JsonSerializer {
    
    private final Gson gson;
    
    public JsonSerializer() {
        this.gson = createGson();
    }
    
    public JsonSerializer(boolean prettyPrint) {
        this.gson = createGson(prettyPrint);
    }
    
    /**
     * Creates a configured Gson instance.
     */
    private static Gson createGson() {
        return createGson(false);
    }
    
    private static Gson createGson(boolean prettyPrint) {
        GsonBuilder builder = new GsonBuilder()
            .registerTypeAdapter(Instant.class, new InstantAdapter())
            .registerTypeAdapter(byte[].class, new ByteArrayAdapter())
            .enableComplexMapKeySerialization();
        
        if (prettyPrint) {
            builder.setPrettyPrinting();
        }
        
        return builder.create();
    }
    
    /**
     * Serializes an object to JSON string.
     */
    public String serialize(Object obj) {
        return gson.toJson(obj);
    }
    
    /**
     * Serializes an object to a file.
     */
    public void serializeToFile(Object obj, Path path) throws IOException {
        String json = serialize(obj);
        Files.writeString(path, json);
    }
    
    /**
     * Serializes an object to a writer.
     */
    public void serializeToWriter(Object obj, Writer writer) {
        gson.toJson(obj, writer);
    }
    
    /**
     * Deserializes a JSON string to an object.
     */
    public <T> T deserialize(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
    
    /**
     * Deserializes a file to an object.
     */
    public <T> T deserializeFromFile(Path path, Class<T> clazz) throws IOException {
        String json = Files.readString(path);
        return deserialize(json, clazz);
    }
    
    /**
     * Deserializes from a reader to an object.
     */
    public <T> T deserializeFromReader(Reader reader, Class<T> clazz) {
        return gson.fromJson(reader, clazz);
    }
    
    /**
     * Parses JSON string to JsonElement.
     */
    public JsonElement parse(String json) {
        return JsonParser.parseString(json);
    }
    
    /**
     * Serializes metadata entries.
     */
    public String serializeMetadata(Map<String, EntryMetadata> metadata) {
        return serialize(metadata);
    }
    
    /**
     * Deserializes metadata entries.
     */
    @SuppressWarnings("unchecked")
    public Map<String, EntryMetadata> deserializeMetadata(String json) {
        return deserialize(json, Map.class);
    }
    
    /**
     * Gets the underlying Gson instance.
     */
    public Gson getGson() {
        return gson;
    }
    
    /**
     * Instant adapter for Gson.
     */
    private static class InstantAdapter implements com.google.gson.JsonSerializer<Instant>,
                                                          com.google.gson.JsonDeserializer<Instant> {
        
        @Override
        public com.google.gson.JsonElement serialize(Instant src, 
                                                     java.lang.reflect.Type typeOfSrc,
                                                     com.google.gson.JsonSerializationContext context) {
            return new com.google.gson.JsonPrimitive(src.toString());
        }
        
        @Override
        public Instant deserialize(com.google.gson.JsonElement json, 
                                  java.lang.reflect.Type typeOfT,
                                  com.google.gson.JsonDeserializationContext context) {
            return Instant.parse(json.getAsString());
        }
    }
    
    /**
     * Byte array adapter for Gson.
     */
    private static class ByteArrayAdapter implements com.google.gson.JsonSerializer<byte[]>,
                                                       com.google.gson.JsonDeserializer<byte[]> {
        
        @Override
        public com.google.gson.JsonElement serialize(byte[] src, 
                                                     java.lang.reflect.Type typeOfSrc,
                                                     com.google.gson.JsonSerializationContext context) {
            return new com.google.gson.JsonPrimitive(java.util.Base64.getEncoder().encodeToString(src));
        }
        
        @Override
        public byte[] deserialize(com.google.gson.JsonElement json, 
                                  java.lang.reflect.Type typeOfT,
                                  com.google.gson.JsonDeserializationContext context) {
            return java.util.Base64.getDecoder().decode(json.getAsString());
        }
    }
    
    /**
     * Builder for creating JSON serializers.
     */
    public static class Builder {
        private boolean prettyPrint = false;
        
        public Builder withPrettyPrint(boolean prettyPrint) {
            this.prettyPrint = prettyPrint;
            return this;
        }
        
        public JsonSerializer build() {
            return new JsonSerializer(prettyPrint);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}
