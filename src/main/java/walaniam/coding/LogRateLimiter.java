package walaniam.coding;

/**
 * Log Rate Limiter (Queue + Sliding Window)
 * <p>
 * Problem:
 * <p>
 * You are given a stream of log events, each represented as a tuple:
 * (timestampInSeconds, messageString)
 * <p>
 * Implement a class Logger with:
 * <p>
 * boolean shouldPrint(int timestamp, String message)
 * Returns true if the log can be printed, meaning that the same message has not been printed in the last 10 seconds.
 * Otherwise, return false.
 *
 * <code>
 * shouldPrint(1, "error")  -> true
 * shouldPrint(2, "error")  -> false
 * shouldPrint(12, "error") -> true   // 12 - 1 >= 10
 * </code>
 *
 * <p>Solution constraints:</p> memory grows with unique messages, non-thread safe.
 *
 */
public interface LogRateLimiter {
    /**
     * @param timestamp milliseconds
     */
    boolean shouldPrint(long timestamp, String message);
}
