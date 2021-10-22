package interesting.g4g;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BinaryTreeUtility {

    int diameter(Node root) {
        if (root == null) {
            return 0;
        }
        Map<Node, Integer> heights = new HashMap<>();

        int maxDiameter = 1;
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            maxDiameter = Math.max(maxDiameter, height(node.left) + height(node.right) + 1);
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }

        return maxDiameter;
    }

    boolean isBST(Node root) {
        try {
            checkBST(root);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private void checkBST(Node node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            checkBST(node.left);
            if (max(node.left) >= node.data) {
                throw new RuntimeException("");
            }
        }
        if (node.right != null) {
            checkBST(node.right);
            if (min(node.right) <= node.data) {
                throw new RuntimeException("");
            }
        }
    }

    private int min(Node node) {
        if (node.left == null) {
            return node.data;
        }
        return Math.min(node.data, min(node.left));
    }

    private int max(Node node) {
        if (node.right == null) {
            return node.data;
        }
        return Math.max(node.data, max(node.right));
    }

    public static void main(String[] args) {
        Node tree = new Node(15);
        tree.left = new Node(7);
        tree.left.left = new Node(1);
        tree.left.left.right = new Node(2);
        tree.left.right = new Node(12);
        tree.left.right.left = new Node(10);
        tree.left.right.right = new Node(14);
        tree.right = new Node(16);
        System.out.println(new interesting.g4g.BinaryTreeUtility().isBST(tree));
    }
}

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
    }
}
