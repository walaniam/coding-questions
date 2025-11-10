package walaniam.coding;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TreeTraversalTest {

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
    @Test
    void traverse() {

        var root = new TreeNode(5);
        var n4 = root.addLeft(4);
        var n8 = root.addRight(8);

        var n11 = n4.addLeft(11);
        n11.addLeft(7);
        n11.addRight(2);

        n8.addLeft(13);
        var n4Right = n8.addRight(4);
        n4Right.addRight(1);

        var underTest = new TreeTraversal(root);

        assertThat(underTest.dfsSum()).isEqualTo(55);
        assertThat(underTest.bfsSum()).isEqualTo(55);
    }

}