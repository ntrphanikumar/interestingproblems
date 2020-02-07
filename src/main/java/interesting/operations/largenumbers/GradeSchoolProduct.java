package interesting.operations.largenumbers;

import static interesting.operations.largenumbers.NumberUtils.randomNumber;
import static interesting.operations.largenumbers.NumberUtils.sum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GradeSchoolProduct implements Product {

    private static final char CHAR_ZERO = '0', CHAR_SPACE = ' ';
    private static final int MAX_SAFE_LENGTH = (Long.MAX_VALUE + "").length() - 1;

    @Override
    public String compute(String multiplier, String multiplicand) {
        if (multiplier.length() + multiplicand.length() <= MAX_SAFE_LENGTH) {
            return String.valueOf(Long.parseLong(multiplier) * Long.parseLong(multiplicand));
        }
        long start = System.nanoTime();
        int blockLength = MAX_SAFE_LENGTH / 2;
        List<Long> multiplierBlocks = splitToBlocks(multiplier, blockLength);
        List<Long> multiplicandBlocks = splitToBlocks(multiplicand, blockLength);
        long end = System.nanoTime();
        System.out.println("Block generation took: " + (end - start));
        start = System.nanoTime();
        List<String> numberToSum = new ArrayList<>();
        for (int i = 0; i < multiplierBlocks.size(); i++) {
            for (int j = 0; j < multiplicandBlocks.size(); j++) {
                String product = String.valueOf(multiplicandBlocks.get(j) * multiplierBlocks.get(i));
                int rightZeros = multiplierBlocks.size() + multiplicandBlocks.size() - i - j - 2;
                if (rightZeros > 0) {
                    String format = "%1$-" + (product.length() + (rightZeros * blockLength)) + "s";
                    numberToSum.add(String.format(format, product).replace(CHAR_SPACE, CHAR_ZERO));
                } else {
                    numberToSum.add(product);
                }
            }
        }
        end = System.nanoTime();
        System.out.println("Number list for sum generation took: " + (end - start));
        return sum(numberToSum, multiplier.length() + multiplicand.length());
    }

    private List<Long> splitToBlocks(String number, int blockLength) {
        String paddedNumber = addLeftPadding(number, blockLength);
        return IntStream.range(0, paddedNumber.length() / blockLength)
                .mapToObj(idx -> paddedNumber.substring(blockLength * (idx), blockLength * (idx + 1)).trim())
                .map(n -> n.length() == 0 ? 0 : Long.parseLong(n)).collect(Collectors.toList());
    }

    private String addLeftPadding(String number, int blockLength) {
        return String.format("%" + (number.length() + blockLength - (number.length() % blockLength)) + "s", number);
    }

    public static void main(String[] args) {
        String first = randomNumber(12);
        String second = randomNumber(12);
        System.out.println(first);
        System.out.println("X");
        System.out.println(second);
        System.out.println("=");
        System.out.println(new GradeSchoolProduct().compute(first, second));
    }
}
