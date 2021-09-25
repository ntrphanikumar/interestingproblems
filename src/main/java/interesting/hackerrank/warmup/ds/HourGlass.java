package interesting.hackerrank.warmup.ds;

import java.util.*;

public class HourGlass {
    public static int hourglassSum(List<List<Integer>> arr) {
        int maxSum = -100;
        for(int row=0; row < arr.size()-2;row++) {
            for(int col=0;col<arr.size()-2;col++) {
                maxSum = Math.max(maxSum, hourglassSum(arr, row, col));
            }
        }
        return maxSum;
    }

    private static int hourglassSum(List<List<Integer>> arr, int starRow, int startCol) {
        if(starRow<0 || starRow>arr.size()-2 || startCol <0 || startCol>arr.size()-2) {
            return -100;
        }
        return arr.get(starRow).get(startCol) + arr.get(starRow).get(startCol+1) + arr.get(starRow).get(startCol+2)
                + arr.get(starRow+1).get(startCol+1)
                + arr.get(starRow+2).get(startCol) + arr.get(starRow+2).get(startCol+1) + arr.get(starRow+2).get(startCol+2);
    }

    public static void main(String[] args) {
        List<List<Integer>> arr = Arrays.asList(
                Arrays.asList(1, 1, 1, 0, 0, 0),
                Arrays.asList(0, 1, 0, 0, 0, 0),
                Arrays.asList(1, 1, 1, 0, 0, 0),
                Arrays.asList(0, 0, 2, 4, 4, 0),
                Arrays.asList(0, 0, 0, 2, 0, 0),
                Arrays.asList(0, 0, 1, 2, 4, 0)
        );
        System.out.println(hourglassSum(arr));
    }
}
