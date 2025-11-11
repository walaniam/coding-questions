package walaniam.coding.leetcode;

import walaniam.coding.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class SumOfRootToLeafNumbers {

    public int sumNumbers(TreeNode root) {
        List<Integer> numbers = new ArrayList<>();
        traverse(root, "", numbers);
        return numbers.stream()
            .reduce(Integer::sum)
            .orElse(0);
    }

    private void traverse(TreeNode node, String digits, List<Integer> numbers) {
        if (node.left != null) {
            traverse(node.left, digits + node.val, numbers);
        }
        if (node.right != null) {
            traverse(node.right, digits + node.val, numbers);
        }
        if (node.right == null && node.left == null) {
            numbers.add(Integer.parseInt(digits + node.val));
        }
    }
    
}
