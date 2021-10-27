package interesting.leetcode.msi;

import java.util.List;
import java.util.stream.Collectors;

public class LargestNumberWhichOccurSameTimes {
    public static int largestNumberWithSameOccurrances(List<Integer> numbers) {
        return numbers.stream().collect(Collectors.groupingBy(a -> a)).entrySet().stream().filter(e -> e.getValue().size() == e.getKey()).reduce((e1, e2) -> e1.getKey() > e2.getKey() ? e1 : e2).get().getKey();
    }

    public static void main(String[] args) {
        System.out.println(largestNumberWithSameOccurrances(java.util.Arrays.asList(1,2,2,5,5,5,5,5,4,4,7,7,7,7,7,7)));
    }
}
