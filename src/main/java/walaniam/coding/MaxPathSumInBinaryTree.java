package walaniam.coding;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Maximum Path Sum in a Binary Tree
 *
 * Problem:
 *
 * Given a binary tree, find the maximum path sum.
 *
 * A path is any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 *
 * The path must contain at least one node, but it does not need to go through the root.
 *
 *        10
 *       /  \
 *      2   10
 *     / \    \
 *    20  1   -25
 *              / \
 *             3   4
 *
 *             Maximum path sum = 20 + 2 + 10 + 10 = 42
 */
public class MaxPathSumInBinaryTree {

    public int maxPathSum(TreeNode root) {
        Set<Integer> nodeMax = new HashSet<>();
        int rootMax = traverse(root, nodeMax);
        Integer max = nodeMax.stream()
            .max(Comparator.comparing(Integer::intValue))
            .orElse(rootMax);
        return Math.max(rootMax, max);
    }

    private static int traverse(TreeNode n, Set<Integer> maxes) {

        int leftBranch = Optional.ofNullable(n.left)
            .map(it -> traverse(it, maxes))
            .orElse(0);
        int rightBranch = Optional.ofNullable(n.right)
            .map(it -> traverse(it, maxes))
            .orElse(0);

        IntStream.of(
            n.val,
            n.val + leftBranch,
            n.val + rightBranch,
            n.val + leftBranch + rightBranch
        ).max().ifPresent(maxes::add);

        return Math.max(
            n.val + Math.max(leftBranch, rightBranch),
            n.val
        );
    }

}
