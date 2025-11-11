package walaniam.coding;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class TreeNode {
    public final int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this.val = value;
    }

    public TreeNode addLeft(int value) {
        if (left != null) {
            throw new IllegalStateException("Already present");
        }
        left = new TreeNode(value);
        return left;
    }

    public TreeNode addRight(int value) {
        if (right != null) {
            throw new IllegalStateException("Already present");
        }
        right = new TreeNode(value);
        return right;
    }

    public int getValue() {
        return val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public List<TreeNode> getChildren() {
        return Stream.of(left, right)
            .filter(Objects::nonNull)
            .toList();
    }
}
