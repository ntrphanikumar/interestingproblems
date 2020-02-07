package interesting.operations.largenumbers;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class NumberUtils {
    private static final char CHAR_ZERO = '0';

    public static String randomNumber(int size) {
        int leftLimit = '0', rightLimit = '9';
        return new Random().ints(leftLimit, rightLimit + 1).limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    public static String sum(List<String> numbers, int maxNumLength) {
        long start = System.nanoTime();
        String sum = "";
        int carry = 0;
        for (int idx = maxNumLength - 1; idx >= 0; idx--) {
            int digitSum = carry;
            for (String number : numbers) {
                int idxToLook = idx - (maxNumLength - number.length());
                if (idxToLook < 0) {
                    continue;
                }
                digitSum += number.charAt(idxToLook) - CHAR_ZERO;
            }
            sum = (digitSum % 10) + sum;
            carry = digitSum / 10;
        }
        sum = carry > 0 ? carry + sum : sum;
        long end = System.nanoTime();
        System.out.println("Sum of " + numbers.size() + " took: " + (end - start));
        return sum;
    }
}
