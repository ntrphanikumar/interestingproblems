package interesting.operations.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LinkedListSum {

    public List<Integer> sum(LinkedList<Integer> number1, LinkedList<Integer> number2) {
        int maxLength = Integer.max(number1.size(), number2.size()) + 1;
        int num1Length = number1.size(), num2Length = number2.size();
        for (int i = 0; i < maxLength - num1Length; i++) {
            number1.add(0, 0);
        }
        for (int i = 0; i < maxLength - num2Length; i++) {
            number2.add(0, 0);
        }
        System.out.println(number1);
        System.out.println(number2);
        List<Integer> sum = new ArrayList<>(maxLength);
        for (int i = 0; i < maxLength; i++) {
            sum.add(0);
        }
        for (int pos = maxLength - 1; pos > 0; pos--) {
            int valAtIdx = sum.get(pos) + number1.get(pos) + number2.get(pos);
            sum.set(pos, valAtIdx % 10);
            sum.set(pos - 1, valAtIdx / 10);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new LinkedListSum().sum(new LinkedList<>(Arrays.asList(1, 2, 3)),
                new LinkedList<>(Arrays.asList(9, 4, 5))));
    }
}
