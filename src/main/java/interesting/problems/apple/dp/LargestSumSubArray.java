package interesting.problems.apple.dp;

import java.util.ArrayList;
import java.util.Arrays;

public class LargestSumSubArray {

    public static int maxSumSubArray(int[] numbers) {
        Integer[] maxSumsForPos = new Integer[numbers.length];
        maxSumsForPos[0] = numbers[0];
        int maxSum = Integer.MIN_VALUE;
        for (int i = 1; i < numbers.length; i++) {
            if (maxSumsForPos[i - 1] < 0) {
                maxSumsForPos[i] = numbers[i];
            } else {
                maxSumsForPos[i] = numbers[i] + maxSumsForPos[i - 1];
            }
            maxSum = Math.max(maxSum, maxSumsForPos[i]);
            System.out.println(Arrays.<Integer>asList(maxSumsForPos));
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] array = {-4, 2, -5, 1, 2, 3, 6, -5, 1};
        System.out.println(maxSumSubArray(array));
    }
}
