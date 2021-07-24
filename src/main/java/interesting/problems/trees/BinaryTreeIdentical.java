package interesting.problems.trees;

public class BinaryTreeIdentical {
    public static void main(String[] args) {
        TreeNode roo1 = new TreeNode(2, new TreeNode(1, null, null), new TreeNode(3, null, null));
        System.out.println(new BinaryTreeIdentical().areIdentical(roo1, new TreeNode(5, null, null)));
    }

    public boolean areIdentical(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        }
        return root1.val == root2.val && areIdentical(root1.left, root2.left) && areIdentical(root1.right, root2.right);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
