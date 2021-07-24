package interesting.operations.multiply;

import interesting.operations.NumberUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleDigitMultiplication {

    private static final char CHAR_ZERO = '0';

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
        return NumberUtils.sum(numbersToSum, maxLength);
    }

    public String compute(String first, String second) {
        return first.length() > second.length() ? computeProduct(first, second) : computeProduct(second, first);
    }
}
