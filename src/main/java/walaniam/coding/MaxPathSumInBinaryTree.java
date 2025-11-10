package walaniam.coding;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        Map<TreeNode, Integer> nodeMax = new HashMap<>();
        int rootMax = traverse(root, nodeMax);
        Optional<Integer> max = nodeMax.values().stream().max(Comparator.comparing(Integer::intValue));
        return max.orElse(rootMax);
    }

    private static int traverse(TreeNode n, Map<TreeNode, Integer> nodeSums) {

        int leftBranch = Optional.ofNullable(n.getLeft())
            .map(it -> traverse(it, nodeSums))
            .orElse(0);
        int rightBranch = Optional.ofNullable(n.getRight())
            .map(it -> traverse(it, nodeSums))
            .orElse(0);

        nodeSums.put(n, n.getValue() + leftBranch + rightBranch);
        return n.getValue() + Math.max(leftBranch, rightBranch);
    }

}
