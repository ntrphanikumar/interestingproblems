package interesting.operations.largenumbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PreComputeProduct {

    private static final char CHAR_ZERO = '0';

    public static String compute(String first, String second) {
        return first.length() > second.length() ? computeProduct(first, second) : computeProduct(second, first);
    }

    private static String computeProduct(String multiplicand, String multiplier) {
        Map<Character, String> productByChar = mathTable(multiplicand);
        List<String> numbersToSum = IntStream.range(0, multiplier.length()).parallel().mapToObj(multiplierIdx -> {
            char multiplierChar = multiplier.charAt(multiplierIdx);
            if (CHAR_ZERO == multiplierChar) {
                return "0";
            }
            int rightPad = multiplier.length() - multiplierIdx - 1;
            return productByChar.get(multiplierChar)
                    + (rightPad > 0 ? String.format("%-" + rightPad + "s", "").replace(' ', CHAR_ZERO) : "");
        }).collect(Collectors.toList());
        return sum(numbersToSum, multiplicand.length() + multiplier.length());
    }

    private static Map<Character, String> mathTable(String number) {
        final Map<Character, String> productByChar = new HashMap<>();
        productByChar.put('1', number);
        IntStream.rangeClosed(2, 9).parallel().forEach(multiplierValue -> {
            int carry = 0;
            String product = "";
            for (int multiplicandIdx = number.length() - 1; multiplicandIdx >= 0; multiplicandIdx--) {
                int digitProduct = ((number.charAt(multiplicandIdx) - CHAR_ZERO) * multiplierValue) + carry;
                if (multiplicandIdx == 0) {
                    product = digitProduct + product;
                } else {
                    product = (digitProduct % 10) + product;
                    carry = digitProduct / 10;
                }
            }
            productByChar.put((char) (CHAR_ZERO + multiplierValue), product);
        });
        return productByChar;
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
        String first = NumberUtils.randomNumber(999);
        String second = NumberUtils.randomNumber(999);
        System.out.println("SingleDigitProduct of");
        System.out.println(first);
        System.out.println("X");
        System.out.println(second);
        long start = System.nanoTime();
        String compute = PreComputeProduct.compute(first, second);
        long end = System.nanoTime();
        System.out.println("=");
        System.out.println(compute);
        System.out.println("Took nanos my multiplication: " + (end - start));
        start = System.nanoTime();
        String bigIntCompute = new BigInteger(first).multiply(new BigInteger(second)).toString();
        System.out.println(bigIntCompute);
        end = System.nanoTime();
        System.out.println("Took nanos bigint: " + (end - start));
        System.out.println(bigIntCompute.equals(compute));
    }
}
