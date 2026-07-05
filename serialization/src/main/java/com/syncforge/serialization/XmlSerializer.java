package com.syncforge.serialization;

import com.syncforge.metadata.EntryMetadata;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * XML serializer for SyncForge data models.
 * Provides XML serialization for compatibility with other systems.
 */
public class XmlSerializer {
    
    private final boolean prettyPrint;
    
    public XmlSerializer() {
        this(true);
    }
    
    public XmlSerializer(boolean prettyPrint) {
        this.prettyPrint = prettyPrint;
    }
    
    /**
     * Serializes an object to XML string.
     */
    public String serialize(Object obj) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            
            // Create root element
            Element root = doc.createElement(obj.getClass().getSimpleName());
            doc.appendChild(root);
            
            // Serialize object properties
            serializeObject(obj, root, doc);
            
            // Convert to string
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            if (prettyPrint) {
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            }
            
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            
            return writer.toString();
            
        } catch (Exception e) {
            throw new IOException("Failed to serialize object to XML", e);
        }
    }
    
    /**
     * Serializes an object to a file.
     */
    public void serializeToFile(Object obj, Path path) throws IOException {
        String xml = serialize(obj);
        Files.writeString(path, xml);
    }
    
    /**
     * Deserializes an XML string to an object.
     */
    @SuppressWarnings("unchecked")
    public <T> T deserialize(String xml, Class<T> clazz) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new org.xml.sax.InputSource(new StringReader(xml)));
            
            Element root = doc.getDocumentElement();
            
            // Deserialize object
            return (T) deserializeObject(root, clazz);
            
        } catch (Exception e) {
            throw new IOException("Failed to deserialize XML to object", e);
        }
    }
    
    /**
     * Deserializes a file to an object.
     */
    public <T> T deserializeFromFile(Path path, Class<T> clazz) throws IOException {
        String xml = Files.readString(path);
        return deserialize(xml, clazz);
    }
    
    /**
     * Serializes an object's properties to an XML element.
     */
    private void serializeObject(Object obj, Element parent, Document doc) {
        if (obj == null) {
            return;
        }
        
        // Handle maps
        if (obj instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) obj;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                Element entryElement = doc.createElement("entry");
                entryElement.setAttribute("key", String.valueOf(entry.getKey()));
                entryElement.setTextContent(String.valueOf(entry.getValue()));
                parent.appendChild(entryElement);
            }
            return;
        }
        
        // Handle collections
        if (obj instanceof java.util.Collection) {
            for (Object item : (java.util.Collection<?>) obj) {
                Element itemElement = doc.createElement("item");
                itemElement.setTextContent(String.valueOf(item));
                parent.appendChild(itemElement);
            }
            return;
        }
        
        // Handle arrays
        if (obj.getClass().isArray()) {
            Object[] array = (Object[]) obj;
            for (Object item : array) {
                Element itemElement = doc.createElement("item");
                itemElement.setTextContent(String.valueOf(item));
                parent.appendChild(itemElement);
            }
            return;
        }
        
        // Handle primitive types and strings
        if (obj instanceof String || obj instanceof Number || obj instanceof Boolean) {
            parent.setTextContent(String.valueOf(obj));
            return;
        }
        
        // Handle other objects using reflection
        try {
            for (java.lang.reflect.Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(obj);
                
                Element fieldElement = doc.createElement(field.getName());
                
                if (value != null) {
                    serializeObject(value, fieldElement, doc);
                }
                
                parent.appendChild(fieldElement);
            }
        } catch (IllegalAccessException e) {
            // Skip inaccessible fields
        }
    }
    
    /**
     * Deserializes an XML element to an object.
     */
    private Object deserializeObject(Element element, Class<?> clazz) {
        // Simplified implementation - would need full reflection-based deserialization
        // For now, return null as placeholder
        return null;
    }
    
    
    /**
     * Builder for creating XML serializers.
     */
    public static class Builder {
        private boolean prettyPrint = true;
        
        public Builder withPrettyPrint(boolean prettyPrint) {
            this.prettyPrint = prettyPrint;
            return this;
        }
        
        public XmlSerializer build() {
            return new XmlSerializer(prettyPrint);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}
