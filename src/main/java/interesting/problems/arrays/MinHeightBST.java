package interesting.problems.arrays;

public class MinHeightBST {

    static class Node {
        final int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    data +
                    (left != null ? ", left=" + left : "") +
                    (right != null ? ", right=" + right : "") +
                    '}';
        }
    }

    static Node minHeightBST(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        return minHeightBST(arr, 0, arr.length - 1);
    }

    private static Node minHeightBST(int arr[], int startIdx, int endIdx) {
        if (startIdx == endIdx) {
            return new Node(arr[startIdx]);
        }
        int middleIdx = (endIdx + startIdx + 1) / 2;
        Node root = new Node(arr[middleIdx]);
        if (startIdx < middleIdx) {
            root.left = minHeightBST(arr, startIdx, middleIdx - 1);
        }
        if (middleIdx < endIdx) {
            root.right = minHeightBST(arr, middleIdx + 1, endIdx);
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println(minHeightBST(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(minHeightBST(new int[]{1, 2, 3, 4, 5}));
    }
}
