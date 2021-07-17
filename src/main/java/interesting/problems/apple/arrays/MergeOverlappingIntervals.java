package interesting.problems.apple.arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MergeOverlappingIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }
        Map<Integer, Integer> rangeMap = new TreeMap<>();
        for (int[] interval : intervals) {
            if (!rangeMap.containsKey(interval[0])) {
                rangeMap.put(interval[0], interval[1]);
            }
            rangeMap.put(interval[0], Math.max(interval[1], rangeMap.get(interval[0])));
        }
        List<Integer[]> mergedIntervals = new ArrayList<>();
        int[] runningInterval = {rangeMap.keySet().iterator().next(), rangeMap.values().iterator().next()};
        for (Map.Entry<Integer, Integer> interval : rangeMap.entrySet()) {
            if (interval.getKey() <= runningInterval[1]) {
                runningInterval[1] = Math.max(interval.getValue(), runningInterval[1]);
            } else {
                mergedIntervals.add(new Integer[]{runningInterval[0], runningInterval[1]});
                runningInterval = new int[]{interval.getKey(), interval.getValue()};
            }
        }
        if (runningInterval != null) {
            mergedIntervals.add(new Integer[]{runningInterval[0], runningInterval[1]});
        }
        int[][] merged = new int[mergedIntervals.size()][2];
        for (int i = 0; i < mergedIntervals.size(); i++) {
            merged[i][0] = mergedIntervals.get(i)[0];
            merged[i][1] = mergedIntervals.get(i)[1];
        }
        return merged;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 5}, {3, 7}, {4, 6}, {6, 8}, {10, 12}, {12, 15}, {16, 18}};
        System.out.println(intervals);
        System.out.println(new MergeOverlappingIntervals().merge(intervals));
    }
}