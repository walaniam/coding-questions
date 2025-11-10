package walaniam.coding;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MaxPathSumInBinaryTreeTest {

    /**
     *        10
     *       /  \
     *      2   10
     *     / \    \
     *    20  1   -25
     *            / \
     *           3   4
     *
     *  20 + 2 + 10 + 10 = 42
     */
    @Test
    void calculate() {

        var root = new TreeNode(10);
        TreeNode l1Left = root.addLeft(2);
        l1Left.addLeft(20);
        l1Left.addRight(1);

        TreeNode l1Right = root.addRight(10);
        TreeNode l2RightRight = l1Right.addRight(-25);
        l2RightRight.addLeft(3);
        l2RightRight.addRight(4);

        var tree = new MaxPathSumInBinaryTree();

        assertThat(tree.maxPathSum(root)).isEqualTo(42);
    }

    @Test
    void calculateAllPositive() {
        /**
         *        5
         *       / \
         *      4   8
         *     /   / \
         *    11  13  4
         *   /  \      \
         *  7    2      1
         *
         * Max path sum = 7 + 11 + 4 + 5 + 8 + 13 = 48
         */
        var root = new TreeNode(5);
        var n4 = root.addLeft(4);
        var n8 = root.addRight(8);

        var n11 = n4.addLeft(11);
        n11.addLeft(7);
        n11.addRight(2);

        n8.addLeft(13);
        var n4Right = n8.addRight(4);
        n4Right.addRight(1);

        var tree = new MaxPathSumInBinaryTree();
        assertThat(tree.maxPathSum(root)).isEqualTo(48);
    }

    @Test
    void calculateWithNegatives() {
        /**
         *        -10
         *        /  \
         *      9    20
         *          /  \
         *         15   7
         *
         * Max path sum = 15 + 20 + 7 = 42
         */
        var root = new TreeNode(-10);
        root.addLeft(9);
        var n20 = root.addRight(20);
        n20.addLeft(15);
        n20.addRight(7);

        var tree = new MaxPathSumInBinaryTree();
        assertThat(tree.maxPathSum(root)).isEqualTo(42);
    }

    @Test
    void calculateSingleNode() {
        /**
         * Single node tree
         *
         * Max path sum = 5
         */
        var root = new TreeNode(5);
        var tree = new MaxPathSumInBinaryTree();
        assertThat(tree.maxPathSum(root)).isEqualTo(5);
    }

    @Test
    void calculateAllNegative() {
        /**
         *        -3
         *       /  \
         *     -2   -1
         *
         * Max path sum = -1
         */
        var root = new TreeNode(-3);
        root.addLeft(-2);
        root.addRight(-1);

        var tree = new MaxPathSumInBinaryTree();
        assertThat(tree.maxPathSum(root)).isEqualTo(-1);
    }

    @Test
    void calculateSkewedTree() {
        /**
         *        1
         *         \
         *          2
         *           \
         *            3
         *             \
         *              4
         *
         * Max path sum = 1 + 2 + 3 + 4 = 10
         */
        var root = new TreeNode(1);
        var n2 = root.addRight(2);
        var n3 = n2.addRight(3);
        n3.addRight(4);

        var tree = new MaxPathSumInBinaryTree();
        assertThat(tree.maxPathSum(root)).isEqualTo(10);
    }

}