package com.syncforge.query;

import com.syncforge.metadata.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueryParserTest {

    @Test
    public void testQueryExpressions() {
        FileEntry file = new FileEntry("src/App.java", 2000, new FileTimestamp(0), new FileMode(FileMode.Type.FILE, 0644), new FileHash("SHA256", "abc123hash"));
        DirectoryEntry dir = new DirectoryEntry("src", new FileTimestamp(0), new FileMode(FileMode.Type.DIRECTORY, 0755));

        QueryExpression expr1 = QueryParser.parse("type:file AND size > 1000");
        assertTrue(expr1.evaluate(file));
        assertFalse(expr1.evaluate(dir));

        QueryExpression expr2 = QueryParser.parse("ext:java");
        assertTrue(expr2.evaluate(file));
        assertFalse(expr2.evaluate(dir));

        QueryExpression expr3 = QueryParser.parse("path contains \"App\"");
        assertTrue(expr3.evaluate(file));
        assertFalse(expr3.evaluate(dir));
        
        QueryExpression expr4 = QueryParser.parse("checksum:abc123");
        assertTrue(expr4.evaluate(file));
    }
}
