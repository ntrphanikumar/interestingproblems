package interesting.operations.largenumbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product {

    private static final char CHAR_ZERO = '0';

    public static String compute(String first, String second) {
        return first.length() > second.length() ? computeProduct(first, second) : computeProduct(second, first);
    }

    private static String computeProduct(String multiplicand, String multiplier) {
        int multiplicandLength = multiplicand.length();
        int multiplierLength = multiplier.length();
        String suffix = "";
        Map<Character, String> productByChar = new HashMap<>();
        productByChar.put('1', multiplicand);
        List<String> numbersToSum = new ArrayList<String>();
        int maxLength = 0;
        for (int multiplierIdx = multiplierLength - 1; multiplierIdx >= 0; multiplierIdx--, suffix += CHAR_ZERO) {
            char multiplierChar = multiplier.charAt(multiplierIdx);
            if (CHAR_ZERO == multiplierChar) {
                continue;
            }
            if (!productByChar.containsKey(multiplierChar)) {
                int carry = 0;
                String product = "";
                int multiplierValue = multiplierChar - CHAR_ZERO;
                for (int multiplicandIdx = multiplicandLength - 1; multiplicandIdx >= 0; multiplicandIdx--) {
                    int digitProduct = ((multiplicand.charAt(multiplicandIdx) - CHAR_ZERO) * multiplierValue) + carry;
                    if (multiplicandIdx == 0) {
                        product = digitProduct + product;
                    } else {
                        product = (digitProduct % 10) + product;
                        carry = digitProduct / 10;
                    }
                }
                productByChar.put(multiplierChar, product);
            }
            String result = productByChar.get(multiplierChar) + suffix;
            numbersToSum.add(result);
            maxLength = Math.max(maxLength, result.length());
        }
        return sum(numbersToSum, maxLength);
    }

    private static String sum(List<String> numbers, int maxNumLength) {
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
        long end = System.nanoTime();
        return carry > 0 ? carry + sum : sum;
    }

    public static void main(String[] args) {
        String first = NumberUtils.randomNumber(9999);
        String second = NumberUtils.randomNumber(9999);
        System.out.println("Product of");
        System.out.println(first);
        System.out.println("X");
        System.out.println(second);
        long start = System.nanoTime();
        String compute = Product.compute(first, second);
        long end = System.nanoTime();
        System.out.println("=");
        System.out.println(compute);
        System.out.println("Took nanos my multiplication: " + (end - start));
        start = System.nanoTime();
        new BigInteger(first).multiply(new BigInteger(second)).toString();
        end = System.nanoTime();
        System.out.println("Took nanos bigint: " + (end - start));
    }
}
