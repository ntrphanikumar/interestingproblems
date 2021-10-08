package interesting.hackerrank;

import java.util.Arrays;
import java.util.List;

public class MagicSquare {
    public static int formingMagicSquare(List<List<Integer>> s) {
        final int[] numbers = new int[s.size() * s.size()];
        s.forEach(l -> l.forEach(v -> numbers[v - 1]++));
        int missing = 1;
        int change = 0;
        for (int i = 1; i <= numbers.length; ) {
            if (numbers[i - 1] > 1) {
                while (missing <= numbers.length) {
                    if (numbers[missing - 1] == 0) {
                        numbers[missing - 1] = 1;
                        numbers[i - 1] -= 1;
                        change += Math.abs(i - missing);
                        missing++;
                        break;
                    }
                    missing++;
                }
            } else {
                i++;
            }
        }
        return change;
    }

    public static void main(String[] args) {
        System.out.println(formingMagicSquare(Arrays.asList(Arrays.asList(4, 8, 2), Arrays.asList(4, 5, 7), Arrays.asList(6, 1, 6))));
    }
}
