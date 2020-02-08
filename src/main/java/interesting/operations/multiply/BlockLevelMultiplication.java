package interesting.operations.multiply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockLevelMultiplication {

    private static final int MAX_SAFE_LENGTH = 19;

    public String compute(String multiplier, String multiplicand) {
        int totalLength = multiplier.length() + multiplicand.length();
        if (totalLength <= MAX_SAFE_LENGTH) {
            return String.valueOf(Long.parseLong(multiplier) * Long.parseLong(multiplicand));
        }

        int blockSize = determineBlockSize(multiplier, multiplicand);
        long pow10BlockSize = (long) Math.pow(10d, (double) blockSize);
        String blockSizeZerosStr = String.valueOf(pow10BlockSize).substring(1);
        String blockSizeSpacesStr = blockSizeZerosStr.replace('0', ' ');

//        long start = System.nanoTime();
        List<Long> multiplierBlocks = splitToBlocks(multiplier, blockSize, blockSizeSpacesStr);
        List<Long> multiplicandBlocks = splitToBlocks(multiplicand, blockSize, blockSizeSpacesStr);
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
            long sum = carry + (numberByPow10Multiplier.get(pow) % pow10BlockSize);
            carry = (numberByPow10Multiplier.get(pow) / pow10BlockSize) + (sum / pow10BlockSize);
            String value = String.valueOf(sum % pow10BlockSize);
            if (pow < maxPow && value.length() < blockSize) {
                value = blockSizeZerosStr.substring(value.length()) + value;
            }
            values[maxPow - pow] = value;
        }
//        System.out.println(Arrays.asList(values));
        return String.join("", values);
    }

    private List<Long> splitToBlocks(String number, int blockSize, String blockSizeSpacesStr) {
        String paddedNumber = addLeftPadding(number, blockSize, blockSizeSpacesStr);
        List<Long> blocks = new ArrayList<>();
        for (int i = 0; i < paddedNumber.length(); i += blockSize) {
            blocks.add(Long.parseLong(paddedNumber.substring(i, i + blockSize).trim()));
        }
        return blocks;
    }

    private String addLeftPadding(String number, int blockSize, String blockSizeSpacesStr) {
        return number.length() % blockSize == 0 ? number
                : (blockSizeSpacesStr + number).substring(number.length() % blockSize);
    }

    private int determineBlockSize(String multiplier, String multiplicand) {
        return 7;
    }
}
