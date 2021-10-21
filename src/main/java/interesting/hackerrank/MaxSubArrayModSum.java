package interesting.hackerrank;

import java.util.List;

public class MaxSubArrayModSum {
    public static long maximumSum(List<Long> a, long m) {
        // Write your code here
        if (a.isEmpty()) {
            return 0;
        }
        long[] prefixSums = prefixSums(a, m);
        if (m == 1) {
            return prefixSums[prefixSums.length - 1];
        }
        long maxOfPrefixSums = maxOf(prefixSums);
        if (maxOfPrefixSums == m - 1) {
            return maxOfPrefixSums;
        }
        return maxSubArraySumOptimized(a, prefixSums, m);
    }

    private static long maxSubArraySumOptimized(List<Long> a, long[] prefixSums, long m) {
        long max = prefixSums[0];
        for (int i = 0; i < prefixSums.length; i++) {
            for (int j = 0; j <= i; j++) {
                long sum = 0;
                if (j == i) {
                    sum = a.get(i) % m;
                } else {
                    long pss = i == 0 ? 0 : prefixSums[i - 1];
                    sum = (prefixSums[j] - pss + m) % m;
                }
                max = sum > max ? sum : max;
            }
        }
        return max;
    }

    private static long maxSubArraySum(List<Long> a, long[] prefixSums, long m) {
        long max = 0;
        for (int startIdx = 0; startIdx < a.size(); startIdx++) {
            for (int endIdx = startIdx; endIdx < a.size(); endIdx++) {
                long sum = 0;
                if (startIdx == endIdx) {
                    sum = a.get(startIdx) % m;
                } else {
                    long pss = startIdx == 0 ? 0 : prefixSums[startIdx - 1];
                    sum = (prefixSums[endIdx] - pss + m) % m;
                }
                max = sum > max ? sum : max;
            }
        }
        return max;
    }

    private static long maxOf(long[] prefixSums) {
        long max = prefixSums[0];
        for (int i = 1; i < prefixSums.length; i++) {
            max = prefixSums[i] > max ? prefixSums[i] : max;
        }
        return max;
    }

    private static long[] prefixSums(List<Long> a, long m) {
        long[] prefixSums = new long[a.size()];
        prefixSums[0] = a.get(0) % m;
        for (int i = 1; i < a.size(); i++) {
            prefixSums[i] = (prefixSums[i - 1] + a.get(i)) % m;
        }
        return prefixSums;
    }

    public static void main(String[] args) {
        System.out.println(maximumSum(java.util.Arrays.asList(1L, 2L, 3L, 4L, 5L), 9));
    }
}
