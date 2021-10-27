package interesting.leetcode.msi;

import java.util.Map;
import java.util.TreeMap;

public class MinStepsToMakePilesEqualHeight {
    public static int minSteps(Integer[] piles) {
        if (piles == null || piles.length <= 1) {
            return 0;
        }
        Map<Integer, Integer> distinctCounts = new TreeMap<>((x, y) -> Integer.compare(y, x));
        for (Integer pile : piles) {
            distinctCounts.put(pile, distinctCounts.getOrDefault(pile, 0) + 1);
        }
        int steps = 0, multiplier = distinctCounts.size() - 1;
        for (int value : distinctCounts.values()) {
            steps += (multiplier-- * value);
        }
        return steps;
    }

    public static void main(String[] args) {
        System.out.println(minSteps(new Integer[]{1, 2, 5}));
        System.out.println(minSteps(new Integer[]{1, 1, 2, 2, 2, 3, 3, 3, 4, 4}));
    }
}
