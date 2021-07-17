package interesting.operations.sum;

import java.util.Map;
import java.util.TreeMap;

public class Base36NumSum {
    private final Map<Character, Integer> base36ToDecimal = new TreeMap<>();
    private final Map<Integer, Character> decimalToBase36 = new TreeMap<>();

    public Base36NumSum() {
        for (int i = 0; i < 10; i++) {
            Character c = (char) ('0' + i);
            base36ToDecimal.put(c, i);
            decimalToBase36.put(i, c);
        }
        for (int i = 0; i < 26; i++) {
            Character c = (char) ('a' + i);
            base36ToDecimal.put(c, i + 10);
            decimalToBase36.put(i + 10, c);
        }
    }

    public String sum(String num1, String num2) {
        int minLength = Math.min(num1.length(), num2.length());
        String result = "";
        int carry = 0;
        int num1ExtraLength = num1.length() - minLength;
        int num2ExtraLength = num2.length() - minLength;
        for (int i = minLength - 1; i >= 0; i--) {
            int val = base36ToDecimal.get(num1.charAt(i + num1ExtraLength)) + base36ToDecimal.get(num2.charAt(i + num2ExtraLength)) + carry;
            carry = val / 36;
            result = decimalToBase36.get(val % 36) + result;
        }

        if (num1ExtraLength > 0) {
            for (int i = num1ExtraLength - 1; i >= 0; i--) {
                System.out.println(i);
                int val = base36ToDecimal.get(num1.charAt(i)) + carry;
                carry = val / 36;
                result = decimalToBase36.get(val % 36) + result;
            }
        }
        if (num2ExtraLength > 0) {
            for (int i = num2ExtraLength; i >= 0; i--) {
                int val = base36ToDecimal.get(num1.charAt(i)) + carry;
                carry = val / 36;
                result = decimalToBase36.get(val % 36) + result;
            }
        }
        return carry > 0 ? decimalToBase36.get(carry) + result : result;
    }

    public static void main(String[] args) {
        System.out.println(new Base36NumSum().sum("abcd", "pqr"));
    }
}
