package interesting.operations.largenumbers;

import static interesting.operations.largenumbers.NumberUtils.randomNumber;
import static interesting.operations.largenumbers.NumberUtils.sum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class GradeSchoolProduct implements Product {

    private static final String LEN9_SPACES_STR = "         ";
    private static final String LEN9_ZEROS_STR = "000000000";
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
        Map<Integer, Long> numberByPow10Multiplier = new TreeMap<>();
        for (int i = 0; i < multiplierBlocks.size(); i++) {
            for (int j = 0; j < multiplicandBlocks.size(); j++) {
                long product = multiplicandBlocks.get(j) * multiplierBlocks.get(i);
                if (product > 0) {
                    int pow10Multiplier = (multiplierBlocks.size() + multiplicandBlocks.size() - i - j - 2)
                            * blockLength;
                    numberByPow10Multiplier.put(pow10Multiplier,
                            numberByPow10Multiplier.getOrDefault(pow10Multiplier, 0l) + product);
                }
            }
        }
        end = System.nanoTime();
        System.out.println("Power map generation took: " + (end - start));

        start = System.nanoTime();
        int maxLength = 0;
        List<String> numbersToSum = new ArrayList<>();
        StringBuilder suffix = new StringBuilder(LEN9_ZEROS_STR);
        for (Entry<Integer, Long> entry : numberByPow10Multiplier.entrySet()) {
            StringBuilder strval = new StringBuilder(entry.getValue().toString());
            if (entry.getKey() > 0) {
                strval.append(suffix);
                suffix.append(LEN9_ZEROS_STR);
            }
            numbersToSum.add(strval.toString());
            maxLength = Math.max(maxLength, strval.length());
        }
        end = System.nanoTime();
        System.out.println("Number list from power map for sum generation took: " + (end - start));
        return sum(numbersToSum, maxLength);
    }

    private List<Long> splitToBlocks(String number, int blockLength) {
        String paddedNumber = addLeftPadding(number, blockLength);
        List<Long> blocks = new ArrayList<>();
        for (int i = 0; i < paddedNumber.length(); i += blockLength) {
            blocks.add(Long.parseLong(paddedNumber.substring(i, i + blockLength).trim()));
        }
        return blocks;
    }

    private String addLeftPadding(String number, int blockLength) {
        return number.length() % blockLength == 0 ? number
                : (LEN9_SPACES_STR + number).substring(number.length() % blockLength);
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
