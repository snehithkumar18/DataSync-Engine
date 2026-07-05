package com.syncforge.path;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GlobMatcherTest {

    @Test
    public void testGlobMatching() {
        GlobMatcher matcher = new GlobMatcher("**/*.java", true);
        assertTrue(matcher.matches("App.java"));
        assertTrue(matcher.matches("src/main/App.java"));
        assertFalse(matcher.matches("src/main/App.class"));

        GlobMatcher matcher2 = new GlobMatcher("docs/**", true);
        assertTrue(matcher2.matches("docs/readme.txt"));
        assertTrue(matcher2.matches("docs/api/index.html"));
        
        GlobMatcher matcher3 = new GlobMatcher("build/*", true);
        assertTrue(matcher3.matches("build/output.log"));
        assertFalse(matcher3.matches("build/sub/output.log"));
    }
}
