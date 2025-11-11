package walaniam.coding;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class TreeTraversal {

    private final TreeNode root;

    public TreeTraversal(TreeNode root) {
        this.root = root;
    }

    public int dfsSum() {
        return dfsSum(root);
    }

    private static int dfsSum(TreeNode n) {
        // DFS, postorder
        int leftBranchSum = Optional.ofNullable(n.getLeft())
            .map(TreeTraversal::dfsSum)
            .orElse(0);
        int rightBranchSum = Optional.ofNullable(n.getRight())
            .map(TreeTraversal::dfsSum)
            .orElse(0);
        return n.getValue() + leftBranchSum + rightBranchSum;
    }

    public int bfsSum() {

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        TreeNode node;
        int sum = 0;
        while ((node = nodeQueue.poll()) != null) {
            nodeQueue.addAll(node.getChildren());
            sum += node.getValue();
        }

        return sum;
    }

}
