package walaniam.coding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LogRateLimiterTest {

    @Test
    void shouldPrint() {

        var underTest = new LogRateLimiterImpl();

        assertTrue(underTest.shouldPrint(1762544953000L, "message"));
        assertFalse(underTest.shouldPrint(1762544954000L, "message"));
        assertTrue(underTest.shouldPrint(1762544964000L, "message"));
        assertFalse(underTest.shouldPrint(1762544967000L, "message"));
        assertTrue(underTest.shouldPrint(17625449854000L, "message"));
        assertTrue(underTest.shouldPrint(17625449854000L, "other message"));
    }
}