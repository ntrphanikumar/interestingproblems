package interesting.operations.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListSum {

    public List<Integer> sum(List<Integer> number1, List<Integer> number2) {
        int maxLength = Integer.max(number1.size(), number2.size()) + 1;
        List<Integer> sum = new ArrayList<>(maxLength);
        for (int i = 0; i < maxLength; i++) {
            sum.add(0);
        }
        for (int pos = 1; pos < maxLength; pos++) {
            int num1Idx = number1.size() - pos;
            int num2Idx = number2.size() - pos;
            int valAtIdx = sum.get(maxLength - pos) + (num1Idx >= 0 ? number1.get(num1Idx) : 0)
                    + (num2Idx >= 0 ? number2.get(num2Idx) : 0);
            sum.set(maxLength - pos, valAtIdx % 10);
            sum.set(maxLength - pos - 1, valAtIdx / 10);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new ListSum().sum(Arrays.asList(1, 2, 3), Arrays.asList(4, 5)));
    }
}
