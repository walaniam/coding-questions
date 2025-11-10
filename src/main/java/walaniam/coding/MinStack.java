package walaniam.coding;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {

    // Main stack
    private final Deque<Integer> stack = new ArrayDeque<>();

    // Auxiliary stack to track min at each level
    private final Deque<Integer> minStack = new ArrayDeque<>();

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        } else {
            minStack.push(minStack.peek()); // duplicate current min
        }
    }

    public Integer pop() {
        if (stack.isEmpty()) return null;
        minStack.pop();
        return stack.pop();
    }

    public Integer top() {
        return stack.peek();
    }

    public Integer getMin() {
        return minStack.peek();
    }

    /**
     * Optional: return the second minimum
     * Only works if there are at least 2 distinct values in stack
     */
    public Integer getSecondMin() {
        if (stack.isEmpty()) return null;

        Integer currentMin = minStack.peek();
        Integer secondMin = null;

        // Traverse minStack from top to bottom until we find a value > currentMin
        for (Integer min : minStack) {
            if (min > currentMin) {
                secondMin = min;
                break;
            }
        }

        return secondMin;
    }

    public void debug() {
        System.out.println("stack: " + stack);
        System.out.println("min  : " + minStack);
    }
}
