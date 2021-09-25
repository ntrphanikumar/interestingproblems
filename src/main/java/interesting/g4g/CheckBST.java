package interesting.g4g;

public class CheckBST {
    class Node {
        int data;
        Node left;
        Node right;
    }

    boolean isBST(Node root) {
        if (root.left == null && root.right == null) {
            return true;
        } else if (root.right == null) {
            return root.left.data < root.data && isBST(root.left);
        } else if (root.left == null) {
            return root.right.data > root.data && isBST(root.right);
        }
        return root.left.data < root.data && root.right.data > root.data && isBST(root.left) && isBST(root.right);
    }
}
