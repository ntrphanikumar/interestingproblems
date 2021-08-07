package interesting.operations.sum;

import java.util.HashMap;
import java.util.Map;
import static java.util.stream.IntStream.range;
import static java.util.stream.Collectors.toMap;
import static java.lang.Character.forDigit;

public class Base36NumSum {
    private static final Map<Character, Integer> base36ToDecimal = range(0, 36).boxed().collect(toMap( Base36NumSum::toBase36, i -> i));
    private static final Map<Integer, Character> decimalToBase36 = range(0, 36).boxed().collect(toMap( i -> i, Base36NumSum::toBase36));

    private static Character toBase36(int num) {
        return forDigit(num, 36);
    }

    public String sum(String num1, String num2) {
        num1 = num1.toLowerCase();
        num2 = num2.toLowerCase();
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = num1.length() - 1, j = num2.length() - 1;
        while (i >= 0 && j >= 0) {
            int val = base36ToDecimal.get(num1.charAt(i--)) + base36ToDecimal.get(num2.charAt(j--)) + carry;
            carry = val / 36;
            result.append(decimalToBase36.get(val % 36));
        }
        while (i >= 0) {
            int val = base36ToDecimal.get(num1.charAt(i--)) + carry;
            carry = val / 36;
            result.append(decimalToBase36.get(val % 36));
        }
        while (j >= 0) {
            int val = base36ToDecimal.get(num2.charAt(j--)) + carry;
            carry = val / 36;
            result.append(decimalToBase36.get(val % 36));
        }
        if (carry > 0) {
            result.append(decimalToBase36.get(carry));
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new Base36NumSum().sum("abcd", "pqr"));
        System.out.println(new Base36NumSum().sum("a6t", "rt5"));
    }
}
