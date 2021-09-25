package interesting.g4g;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SubArraySum {
    static ArrayList<Integer> subarraySum(int[] arr, int n, int s) {
        if (n == 0) {
            return new ArrayList<>();
        }
        if (arr[0] == s) {
            return new ArrayList<>(Arrays.asList(0, 0));
        }
        int startIdx = 0, endIdx = 1, currSum = arr[0];
        while (startIdx < endIdx && endIdx < n) {
            if (currSum == s) {
                break;
            }
            currSum += arr[endIdx];
            if (currSum > s) {
                currSum -= arr[startIdx];
                startIdx++;
            } else {
                endIdx++;
            }
        }
        System.out.println(startIdx + " " + endIdx + " " + currSum);
        return new ArrayList<Integer>(Arrays.asList(startIdx, endIdx));
    }

    public static void main(String[] args) {
        subarraySum(new int[]{1, 2, 3, 7, 5}, 5, 12);
    }
}
