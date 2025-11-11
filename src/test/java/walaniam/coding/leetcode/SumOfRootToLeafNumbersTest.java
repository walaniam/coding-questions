package walaniam.coding.leetcode;

import org.junit.jupiter.api.Test;
import walaniam.coding.TreeNode;

class SumOfRootToLeafNumbersTest {

    @Test
    void testSum() {

        var root = new TreeNode(4);
        var n9 = root.addLeft(9);
        var n0 = root.addRight(0);

        n9.addLeft(5);
        n9.addRight(1);


        SumOfRootToLeafNumbers underTest = new SumOfRootToLeafNumbers();
        int sum = underTest.sumNumbers(root);

        System.out.println(sum);

    }


}