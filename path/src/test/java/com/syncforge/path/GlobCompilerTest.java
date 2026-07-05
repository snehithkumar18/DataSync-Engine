package com.syncforge.path;

import com.syncforge.core.exceptions.ParseException;
import com.syncforge.core.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GlobCompilerTest {

    @Test
    public void testBasicCompilationAndMatching() {
        GlobDfaMatcher matcher = GlobCompiler.compile("src/main/*.java", true);
        assertTrue(matcher.matches("src/main/App.java"));
        assertTrue(matcher.matches("src/main/Main.java"));
        assertFalse(matcher.matches("src/main/App.class"));
        assertFalse(matcher.matches("src/main/subdir/App.java"));
    }

    @Test
    public void testRecursiveWildcard() {
        GlobDfaMatcher matcher = GlobCompiler.compile("src/**/*.java", true);
        assertTrue(matcher.matches("src/App.java"));
        assertTrue(matcher.matches("src/main/App.java"));
        assertTrue(matcher.matches("src/main/java/com/syncforge/App.java"));
        assertFalse(matcher.matches("src/main/java/com/syncforge/App.class"));
        assertFalse(matcher.matches("other/App.java"));
    }

    @Test
    public void testWildcardChar() {
        GlobDfaMatcher matcher = GlobCompiler.compile("file?.txt", true);
        assertTrue(matcher.matches("file1.txt"));
        assertTrue(matcher.matches("fileA.txt"));
        assertFalse(matcher.matches("file12.txt"));
        assertFalse(matcher.matches("file.txt"));
        assertFalse(matcher.matches("file/.txt"));
    }

    @Test
    public void testCharacterClasses() {
        GlobDfaMatcher matcher1 = GlobCompiler.compile("file[0-9].txt", true);
        assertTrue(matcher1.matches("file3.txt"));
        assertFalse(matcher1.matches("fileA.txt"));

        GlobDfaMatcher matcher2 = GlobCompiler.compile("file[!0-9].txt", true);
        assertTrue(matcher2.matches("fileA.txt"));
        assertFalse(matcher2.matches("file3.txt"));
    }

    @Test
    public void testAlternatesGroup() {
        GlobDfaMatcher matcher = GlobCompiler.compile("images/*.{png,jpg,gif}", true);
        assertTrue(matcher.matches("images/logo.png"));
        assertTrue(matcher.matches("images/photo.jpg"));
        assertTrue(matcher.matches("images/anim.gif"));
        assertFalse(matcher.matches("images/doc.pdf"));
    }

    @Test
    public void testNestedGroups() {
        GlobDfaMatcher matcher = GlobCompiler.compile("docs/*.{pdf,{doc,docx}}", true);
        assertTrue(matcher.matches("docs/report.pdf"));
        assertTrue(matcher.matches("docs/manual.doc"));
        assertTrue(matcher.matches("docs/notes.docx"));
        assertFalse(matcher.matches("docs/image.png"));
    }

    @Test
    public void testCaseInsensitivity() {
        GlobDfaMatcher matcher = GlobCompiler.compile("**/*.JAVA", false);
        assertTrue(matcher.matches("src/App.java"));
        assertTrue(matcher.matches("src/APP.JAVA"));
    }

    @Test
    public void testUnclosedGroupOrClassThrows() {
        assertThrows(ParseException.class, () -> GlobCompiler.compile("file[a-z.txt", true));
    }

    @Test
    public void testPathMatcherIncludesExcludes() {
        List<String> includes = List.of("src/**/*.java", "docs/**/*.md");
        List<String> excludes = List.of("**/temp/**", "**/*Test.java");

        PathMatcher matcher = new PathMatcher(includes, excludes, true);

        // Matches includes, not in excludes
        assertTrue(matcher.matches("src/main/App.java"));
        assertTrue(matcher.matches("docs/readme.md"));

        // Matches excludes
        assertFalse(matcher.matches("src/main/AppTest.java"));
        assertFalse(matcher.matches("src/temp/App.java"));

        // Does not match includes
        assertFalse(matcher.matches("src/main/App.class"));
    }
}
