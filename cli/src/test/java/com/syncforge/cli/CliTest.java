package com.syncforge.cli;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class CliTest {

    @Test
    public void testCliBootAndHelp() {
        // Assert we can call Main.main with no arguments cleanly without exceptions
        assertDoesNotThrow(() -> {
            Main.main(new String[]{});
        });
    }
}
