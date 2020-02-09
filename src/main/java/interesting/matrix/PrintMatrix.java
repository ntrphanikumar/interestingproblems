package interesting.matrix;

import java.util.ArrayList;
import java.util.List;

public class PrintMatrix {

    /**
     * Prints matrix in clockwise direction
     * 
     * @param matrix
     */
    public void printInClockWise(String[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Invalid matrix");
        }
        int size = matrix.length;
        List<String> result = new ArrayList<>();
        if (size == 1) {
            result.add(matrix[0][0]);
        } else {
            for (int i = 0; i < Math.ceil(((double) size) / ((double) 2)); i++) {
                result.addAll(getBoundary(matrix, i, size));
            }
        }
        System.out.println(result);
    }

    private List<String> getBoundary(String[][] matrix, int level, int size) {
        List<String> boundary = new ArrayList<String>();
        for (int col = level; col < size - level; col++) {
            boundary.add(matrix[level][col]);
        }
        for (int row = level + 1; row < size - level; row++) {
            boundary.add(matrix[row][size - level - 1]);
        }
        for (int col = size - level - 2; col >= level; col--) {
            boundary.add(matrix[size - level - 1][col]);
        }
        for (int row = level + 1; row < size - level - 1; row++) {
            boundary.add(matrix[size - level - 1][level]);
        }
        return boundary;
    }

    public static void main(String[] args) {
        new PrintMatrix().printInClockWise(randomMatrix(3));
    }

    private static String[][] randomMatrix(int len) {
        String[][] matrix = new String[len][len];
        for (int row = 0; row < len; row++) {
            for (int col = 0; col < len; col++) {
                matrix[row][col] = (char) ((int) 'a' + col) + "";
            }
        }
        return matrix;
    }
}
