package interesting.problems.apple.trees;

public class MirrorBinaryTree {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this(val, null, null);
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public void printInOrder() {
            if (left != null) {
                left.printInOrder();
            }
            System.out.print(val + " ");
            if (right != null) {
                right.printInOrder();
            }
        }
    }

    public TreeNode mirror(TreeNode node) {
        if (node == null) {
            return null;
        }
        return new TreeNode(node.val, mirror(node.right), mirror(node.left));
    }

    public static void main(String[] args) {
        TreeNode roo1 = new TreeNode(20, new TreeNode(50, new TreeNode(75), new TreeNode(25)), new TreeNode(200, null, new TreeNode(300)));
        roo1.printInOrder();
        System.out.println();
        new MirrorBinaryTree().mirror(roo1).printInOrder();
    }
}
