package walaniam.coding.leetcode;

import org.junit.jupiter.api.Test;
import walaniam.coding.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SumOfRootToLeafNumbersTest {

    private final SumOfRootToLeafNumbers underTest = new SumOfRootToLeafNumbers();

    @Test
    void testSum() {

        var root = new TreeNode(4);
        var n9 = root.addLeft(9);
        var n0 = root.addRight(0);

        n9.addLeft(5);
        n9.addRight(1);

        int sum = underTest.sumNumbers(root);
        assertEquals(1026, sum);
    }

    @Test
    void testSingleNode() {
        var root = new TreeNode(4);
        int sum = underTest.sumNumbers(root);
        assertEquals(4, sum);
    }

    @Test
    void testOneLevelDepthTree() {
        var root = new TreeNode(4);
        root.addLeft(1);
        root.addRight(0);
        int sum = underTest.sumNumbers(root);
        assertEquals(81, sum);
    }

    @Test
    void testLeftBranchOnlyTree() {
        var root = new TreeNode(4);
        root
            .addLeft(1)
            .addLeft(1)
            .addLeft(1);
        int sum = underTest.sumNumbers(root);
        assertEquals(4111, sum);
    }

    @Test
    void testNonZeroLeafTree() {
        var root = new TreeNode(0);
        root
            .addLeft(0)
            .addRight(1);
        int sum = underTest.sumNumbers(root);
        assertEquals(1, sum);
    }

}