package interesting.g4g;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuerySum {

    static class Solution {
        List<Integer> querySum(int n, int arr[], int q, int queries[]) {
            int[] sum = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                sum[i] = sum[i - 1] + arr[i - 1];
            }

            List<Integer> results = new ArrayList<>();
            for (int i = 0; i < 2 * q; i += 2) {
                results.add(sum[queries[i + 1]] - sum[queries[i]] + arr[queries[i] - 1]);
            }
            return results;
        }
    }

    public static void main(String[] args) {
        Solution solution = new QuerySum.Solution();
        List<Integer> result = solution.querySum(4, new int[]{1, 2, 3, 4}, 2, new int[]{1, 4, 2, 3});
        System.out.println(result);
    }
}
