package interesting.leetcode.msi;

import java.util.HashSet;
import java.util.Set;

public class JumpGame {
    public static boolean canReach(int[] arr, int start) {
        return canReach(arr, start, new HashSet<>());
    }

    private static boolean canReach(int[] arr, int pos, Set<Integer> visited) {
        if (pos < 0 || pos >= arr.length || visited.contains(pos)) {
            return false;
        }
        if (arr[pos] == 0) {
            return true;
        }
        visited.add(pos);
        return canReach(arr, pos - arr[pos], visited) || canReach(arr, pos + arr[pos], visited);
    }

    public static void main(String[] args) {
        System.out.println(canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 5));
        System.out.println(canReach(new int[]{4,2,3,0,3,1,2}, 0));
        System.out.println(canReach(new int[]{4,2,3,0,3,1,2}, 0));
    }
}
