package walaniam.coding;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MinStackTest {

    @Test
    void pushPop() {

        var stack = new MinStack();
        stack.push(5);
        stack.push(3);
        stack.push(7);
        stack.push(3);
        stack.push(2);

        stack.debug();

        assertThat(stack.getMin()).isEqualTo(2);
        assertThat(stack.getSecondMin()).isEqualTo(3);

        stack.pop();
        stack.debug();

        assertThat(stack.getMin()).isEqualTo(3);
        assertThat(stack.getSecondMin()).isEqualTo(3);

        stack.pop();
        stack.debug();

        assertThat(stack.getMin()).isEqualTo(3);
        assertThat(stack.getSecondMin()).isEqualTo(5);
    }

    @Test
    void pushSingleElement() {
        MinStack s = new MinStack();
        s.push(5);

        assertThat(s.top()).isEqualTo(5);
        assertThat(s.getMin()).isEqualTo(5);
    }

    @Test
    void pushIncreasingOrder() {
        MinStack s = new MinStack();

        s.push(3);
        s.push(5);
        s.push(7);

        assertThat(s.getMin()).isEqualTo(3);

        assertThat(s.pop()).isEqualTo(7);
        assertThat(s.getMin()).isEqualTo(3);

        assertThat(s.pop()).isEqualTo(5);
        assertThat(s.getMin()).isEqualTo(3);
    }

    @Test
    void pushDecreasingOrder() {
        MinStack s = new MinStack();

        s.push(10);
        s.push(7);
        s.push(2);

        assertThat(s.getMin()).isEqualTo(2);

        assertThat(s.pop()).isEqualTo(2);
        assertThat(s.getMin()).isEqualTo(7);

        assertThat(s.pop()).isEqualTo(7);
        assertThat(s.getMin()).isEqualTo(10);
    }

    @Test
    void interleavedPushPop() {
        MinStack s = new MinStack();

        s.push(4);
        s.push(2);
        s.push(6);
        s.push(1);

        assertThat(s.getMin()).isEqualTo(1);

        assertThat(s.pop()).isEqualTo(1);
        assertThat(s.getMin()).isEqualTo(2);

        assertThat(s.pop()).isEqualTo(6);
        s.debug();
        assertThat(s.getMin()).isEqualTo(2);

        assertThat(s.pop()).isEqualTo(2);
        assertThat(s.getMin()).isEqualTo(4);
    }

    @Test
    void withDuplicates() {
        MinStack s = new MinStack();

        s.push(4);
        s.push(4);
        s.push(4);

        assertThat(s.getMin()).isEqualTo(4);

        s.pop();
        assertThat(s.getMin()).isEqualTo(4);

        s.pop();
        assertThat(s.getMin()).isEqualTo(4);
    }

    @Test
    void complexMixedOperations() {
        MinStack s = new MinStack();

        s.push(5);  // min = 5
        s.push(1);  // min = 1
        s.push(3);  // min = 1
        s.pop();    // pop 3 → min = 1
        s.push(0);  // min = 0
        s.push(4);  // min = 0

        assertThat(s.getMin()).isEqualTo(0);

        s.pop();    // pop 4 → min = 0
        assertThat(s.getMin()).isEqualTo(0);

        s.pop();    // pop 0 → min = 1
        assertThat(s.getMin()).isEqualTo(1);

        s.pop();    // pop 1 → min = 5
        assertThat(s.getMin()).isEqualTo(5);
    }

    @Test
    void secondMinShouldNeverBreakMin() {
        MinStack s = new MinStack();

        s.push(8);
        s.push(3);
        s.push(5);
        s.push(3);

        // getSecondMin must not corrupt anything
        Integer sm = s.getSecondMin();
        assertThat(sm).isEqualTo(3);

        assertThat(s.getMin()).isEqualTo(3);
        assertThat(s.top()).isEqualTo(3);
    }

    @Test
    void failsWhenMinAppearsAfterLargerValues() {
        MinStack s = new MinStack();

        // Push in this specific order
        s.push(5);   // min = 5
        s.push(3);   // min = 3
        s.push(4);   // min = 3
        s.push(2);   // min = 2
        s.push(6);   // min = 2

        assertThat(s.getMin()).isEqualTo(2);

        // Pop top (6)
        assertThat(s.pop()).isEqualTo(6);
        assertThat(s.getMin()).isEqualTo(2);

        // Pop (2), now the min should go back to 3
        assertThat(s.pop()).isEqualTo(2);

        // ✅ Expected min = 3
        // ❌ Your implementation returns 2 or 4 or something else (depends on internal corruption)
        assertThat(s.getMin()).isEqualTo(3);
    }

    @Test
    void complexMixedSequenceBreaksInvariant() {
        MinStack s = new MinStack();

        s.push(5);  // min = 5
        s.push(1);  // min = 1
        s.push(4);  // min = 1
        s.push(1);  // min = 1
        s.push(3);  // min = 1
        s.push(2);  // min = 1

        // Pop a few non-min values
        assertThat(s.pop()).isEqualTo(2);
        assertThat(s.getMin()).isEqualTo(1);

        assertThat(s.pop()).isEqualTo(3);
        assertThat(s.getMin()).isEqualTo(1);

        // Pop a MINIMUM (1) — stack should revert to previous minimum (= 1)
        assertThat(s.pop()).isEqualTo(1);

        // ✅ expected min is STILL 1
        // ❌ your implementation returns something else
        assertThat(s.getMin()).isEqualTo(1);

        // Pop the LAST minimum
        assertThat(s.pop()).isEqualTo(4);

        // ✅ expected min is now 1 (the first one)
        assertThat(s.getMin()).isEqualTo(1);

        // Pop the first "1"
        assertThat(s.pop()).isEqualTo(1);

        // ✅ expected min is now 5
        assertThat(s.getMin()).isEqualTo(5);
    }

    @Test
    void breaksWhenValuesRepeatInStackOrderButNotInMinOrder() {
        MinStack s = new MinStack();

        // Force minStack to reorder internally
        s.push(5);  // min=5
        s.push(2);  // min=2
        s.push(4);  // min=2
        s.push(3);  // min=2
        s.push(2);  // second "2" at deeper level
        s.push(4);  // deeper than both 2's

    /*
        Correct min history at each level:
        [5]
        [2]
        [2]
        [2]
        [2]
        [2]

        Your minStack history will be incorrectly sorted like:
        [2,2,3,4,4,5]   // or some variant
    */

        // Pop top (4) - OK
        assertThat(s.pop()).isEqualTo(4);
        assertThat(s.getMin()).isEqualTo(2);

        // Pop the 2 that is NOT the first 2
        assertThat(s.pop()).isEqualTo(2);

        // ✅ expected min STILL 2 (because first 2 remains)
        // ❌ your code removes the WRONG 2 from minStack
        assertThat(s.getMin()).isEqualTo(2);

        // Now pop 3 (NOT a min)
        assertThat(s.pop()).isEqualTo(3);

        // ✅ expected min STILL 2
        // ❌ your code corrupts ordering and returns wrong value
        assertThat(s.getMin()).isEqualTo(2);

        // Now pop 4 (not connected to min sequence)
        assertThat(s.pop()).isEqualTo(4);

        // ✅ expected min STILL 2
        assertThat(s.getMin()).isEqualTo(2);

        // Finally pop THE FIRST 2
        assertThat(s.pop()).isEqualTo(2);

        // ✅ expected min becomes 5
        // ❌ your code returns something else
        assertThat(s.getMin()).isEqualTo(5);
    }

}