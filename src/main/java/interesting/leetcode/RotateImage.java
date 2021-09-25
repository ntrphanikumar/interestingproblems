package interesting.leetcode;

public class RotateImage {

    public void rotate(int[][] matrix) {
        for (int i = matrix.length - 1; i >= matrix.length / 2; i--) {
            rotate(matrix, i);
        }
    }

    private void rotate(int[][] matrix, int level) {
        if (2 * level < matrix.length) {
            return;
        }
        
    }
}
