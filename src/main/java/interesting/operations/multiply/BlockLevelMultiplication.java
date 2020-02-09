package interesting.operations.multiply;

import interesting.operations.NumberUtils;

public class BlockLevelMultiplication {

    public String compute(String multiplier, String multiplicand) {
        final int longRange = (Long.MAX_VALUE + "").length() - 1;
        if (multiplier.length() + multiplicand.length() <= longRange) {
            return String.valueOf(Long.parseLong(multiplier) * Long.parseLong(multiplicand));
        }
        int blockSize = determineBlockSize(multiplier, multiplicand);
        return sum(powerMap(splitToBlocks(multiplier, blockSize), splitToBlocks(multiplicand, blockSize)), blockSize);
    }

    private long[] powerMap(long[] multiplier, long[] multiplicand) {
//        long start = System.nanoTime();
        int totalBlocksLength = multiplier.length + multiplicand.length;
        long[] values = new long[totalBlocksLength - 1];
        for (int i = 0; i < multiplier.length; i++) {
            for (int j = 0; j < multiplicand.length; j++) {
                values[totalBlocksLength - i - j - 2] += multiplicand[j] * multiplier[i];
            }
        }
//        long end = System.nanoTime();
//        System.out.println("Power list generation took: " + (end - start));
        return values;
    }

    private String sum(long[] numbers, int blockSize) {
//        long start = System.nanoTime();
        final long pow10BlockSize = (long) Math.pow(10d, (double) blockSize);
        final String blockSizeZerosStr = String.valueOf(pow10BlockSize).substring(1);
        String[] values = new String[numbers.length];
        long carry = 0;
        int maxPow = numbers.length - 1;
        String emptyStr = "";
        for (int pow = 0; pow < numbers.length; pow++) {
            if (pow == maxPow) {
                long sum = numbers[pow] + carry;
                values[maxPow - pow] = sum == 0 ? emptyStr : String.valueOf(sum);
                break;
            }
            long sum = carry + (numbers[pow] % pow10BlockSize);
            carry = (numbers[pow] / pow10BlockSize) + (sum / pow10BlockSize);
            String value = String.valueOf(sum % pow10BlockSize);
            if (pow < maxPow && value.length() < blockSize) {
                value = blockSizeZerosStr.substring(value.length()) + value;
            }
            values[maxPow - pow] = value;
        }

        String result = String.join("", values);
//        long end = System.nanoTime();
//        System.out.println("Sum took: " + (end - start));
        return result;
    }

    private long[] splitToBlocks(String number, int blockSize) {
//        long start = System.nanoTime();
        int idxAdj = number.length() % blockSize > 0 ? 0 : 1;
        long[] blocksArray = new long[1 - idxAdj + number.length() / blockSize];
        for (int idx = number.length(); idx > 0; idx -= blockSize) {
            int beginIndex = idx - blockSize;
            blocksArray[(idx / blockSize) - idxAdj] = Long
                    .parseLong(number.substring(beginIndex < 0 ? 0 : beginIndex, idx));
        }
//        long end = System.nanoTime();
//        System.out.println("Block generation took: " + (end - start));
        return blocksArray;
    }

    private int determineBlockSize(String multiplier, String multiplicand) {
        return 7;
    }

    public static void main(String[] args) {
        String first = NumberUtils.randomNumber(10);
        String second = NumberUtils.randomNumber(10);
        System.out.println();
        new BlockLevelMultiplication().compute(first, second);
    }

}
