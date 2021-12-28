package interesting.problems;

public class DeleteIslands {

    public static int[][] deleteIslands(int[][] matrix) {
        if (matrix.length < 3) {
            return matrix;
        }
        return deleteIslands(matrix, 0);
    }

    private static int[][] deleteIslands(int[][] matrix, int level) {
        int n = matrix.length;
        System.out.println(level + " " + n);
        if (level >= (n / 2) + (n % 2)) {
            return matrix;
        }
        if (n / 2 == level && n % 2 == 1) {
            if (matrix[level][level] == 1) {
                if (matrix[level][level - 1] == 0 && matrix[level][level + 1] == 0 && matrix[level - 1][level] == 0 && matrix[level + 1][level] == 0) {
                    matrix[level][level] = 0;
                }
            }
            return matrix;
        }
        for (int i = level; i < n - level; i++) {
            if (level > 0) {
                if (matrix[level][i] == 1) {
                    if (matrix[level - 1][i] == 0 && matrix[level][i - 1] == 1) {
                        matrix[level][i] = 0;
                    }
                }
                if (matrix[level][n - i - 1] == 1) {

                }
                if (matrix[i][level] == 1) {

                }
                if (matrix[n - i - 1][level] == 1) {

                }
            }
        }
        return deleteIslands(matrix, level + 1);
    }

    public static void main(String[] args) {
        deleteIslands(new int[7][7]);
    }
}
