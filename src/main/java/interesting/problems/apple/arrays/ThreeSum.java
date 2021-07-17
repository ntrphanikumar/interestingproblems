package interesting.problems.apple.arrays;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ThreeSum {

    public boolean hasSum(Integer[] numbers, int sum) {
        Integer[] sortedNumbers = Arrays.<Integer>asList(numbers).stream().sorted().collect(Collectors.toList()).toArray(new Integer[numbers.length]);
        for (int i = 0; i < sortedNumbers.length; i++) {
            if (hasTwoNumbersSum(sortedNumbers, sum - sortedNumbers[i], i + 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasTwoNumbersSum(Integer[] sortedList, int sum, int startIdx) {
        for (int i = startIdx; i < sortedList.length; i++) {
            for (int j = startIdx + 1; j < sortedList.length; j++) {
                if (sum == sortedList[i] + sortedList[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Integer[] array = {3, 7, 1, 2, 8, 4, 5};
        System.out.println(new ThreeSum().hasSum(array, 10));
    }
}
