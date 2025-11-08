package walaniam.coding;

import java.util.HashMap;
import java.util.Map;

public class LogRateLimiterImpl implements LogRateLimiter {

    /**
     * 10 seconds window.
     */
    private static final long WINDOW = 10_000;

    private final Map<String, Long> lastSeen = new HashMap<>();

    @Override
    public boolean shouldPrint(long timestamp, String message) {
        synchronized (lastSeen) {
            Long previous = lastSeen.get(message);
            boolean shouldPrint = (previous == null) || isOutOfWindow(timestamp, previous);
            if (shouldPrint) {
                lastSeen.put(message, timestamp);
            }
            return shouldPrint;
        }
    }

    private static boolean isOutOfWindow(long current, long previous) {
        return current - previous >= WINDOW;
    }
}
