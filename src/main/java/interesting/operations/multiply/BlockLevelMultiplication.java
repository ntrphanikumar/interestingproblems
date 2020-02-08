package interesting.operations.multiply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockLevelMultiplication {

    private static final int BLOCK_SIZE = 7;
    private static final long TEN_POW_BLOCKSIZE = (long) Math.pow(10d, (double) BLOCK_SIZE);
    private static final String LEN_BLOCKSIZE_ZEROS_STR = String.valueOf(TEN_POW_BLOCKSIZE).substring(1);
    private static final String LEN_BLOCKSIZE_SPACES_STR = LEN_BLOCKSIZE_ZEROS_STR.replace('0', ' ');
    private static final int MAX_SAFE_LENGTH = 19;

    public String compute(String multiplier, String multiplicand) {
        if (multiplier.length() + multiplicand.length() <= MAX_SAFE_LENGTH) {
            return String.valueOf(Long.parseLong(multiplier) * Long.parseLong(multiplicand));
        }
//        long start = System.nanoTime();
        List<Long> multiplierBlocks = splitToBlocks(multiplier);
        List<Long> multiplicandBlocks = splitToBlocks(multiplicand);
//        long end = System.nanoTime();
//        System.out.println("Block generation took: " + (end - start));

//        start = System.nanoTime();
        int totalBlocksLength = multiplierBlocks.size() + multiplicandBlocks.size();
        Map<Integer, Long> numberByPow10Multiplier = new HashMap<>();
        int maxPow = 0;
        for (int i = 0; i < multiplierBlocks.size(); i++) {
            for (int j = 0; j < multiplicandBlocks.size(); j++) {
                int pow10Multiplier = (totalBlocksLength - i - j - 2);
                numberByPow10Multiplier.put(pow10Multiplier, numberByPow10Multiplier.getOrDefault(pow10Multiplier, 0l)
                        + (multiplicandBlocks.get(j) * multiplierBlocks.get(i)));
                maxPow = Math.max(maxPow, pow10Multiplier);
            }
        }
//        end = System.nanoTime();
//        System.out.println("Power map generation took: " + (end - start));
//        System.out.println(numberByPow10Multiplier);
//        System.out.println(maxPow);

        String[] values = new String[maxPow + 1];
        long carry = 0;
        for (int pow = 0; pow <= maxPow; pow++) {
            if (pow == maxPow) {
                long sum = numberByPow10Multiplier.get(pow) + carry;
                values[maxPow - pow] = sum == 0 ? "" : String.valueOf(sum);
                break;
            }
            long sum = carry + (numberByPow10Multiplier.get(pow) % TEN_POW_BLOCKSIZE);
            carry = (numberByPow10Multiplier.get(pow) / TEN_POW_BLOCKSIZE) + (sum / TEN_POW_BLOCKSIZE);
            String value = String.valueOf(sum % TEN_POW_BLOCKSIZE);
            if (pow < maxPow && value.length() < BLOCK_SIZE) {
                value = LEN_BLOCKSIZE_ZEROS_STR.substring(value.length()) + value;
            }
            values[maxPow - pow] = value;
        }
//        System.out.println(Arrays.asList(values));
        return String.join("", values);
    }

    private List<Long> splitToBlocks(String number) {
        String paddedNumber = addLeftPadding(number);
        List<Long> blocks = new ArrayList<>();
        for (int i = 0; i < paddedNumber.length(); i += BLOCK_SIZE) {
            blocks.add(Long.parseLong(paddedNumber.substring(i, i + BLOCK_SIZE).trim()));
        }
        return blocks;
    }

    private String addLeftPadding(String number) {
        return number.length() % BLOCK_SIZE == 0 ? number
                : (LEN_BLOCKSIZE_SPACES_STR + number).substring(number.length() % BLOCK_SIZE);
    }
}
