package interesting.operations.multiply;

import interesting.operations.NumberUtils;

public class BlockLevelMultiplication {

    private static final int MAX_SAFE_LENGTH = 19;

    private static class BlockMultiplication {

        private final int blockSize;
        private final long pow10BlockSize;
        private final String blockSizeZerosStr, multiplier, multiplicand;

        private BlockMultiplication(String multiplier, String multiplicand) {
            this.multiplier = multiplier;
            this.multiplicand = multiplicand;
            blockSize = determineBlockSize(multiplier, multiplicand);
            pow10BlockSize = (long) Math.pow(10d, (double) blockSize);
            blockSizeZerosStr = String.valueOf(pow10BlockSize).substring(1);
        }

        public String perform() {
            return sum(powerMap(splitToBlocks(multiplier), splitToBlocks(multiplicand)));
        }

        private long[] powerMap(Long[] multiplierBlocks, Long[] multiplicandBlocks) {
            long start = System.nanoTime();
            int totalBlocksLength = multiplierBlocks.length + multiplicandBlocks.length;
            long[] values = new long[totalBlocksLength - 1];
            for (int i = 0; i < multiplierBlocks.length; i++) {
                for (int j = 0; j < multiplicandBlocks.length; j++) {
                    values[totalBlocksLength - i - j - 2] += multiplicandBlocks[j] * multiplierBlocks[i];
                }
            }
            long end = System.nanoTime();
            System.out.println("Power list generation took: " + (end - start));
            return values;
        }

        private String sum(long[] powerList) {
            long start = System.nanoTime();
            String[] values = new String[powerList.length];
            long carry = 0;
            int maxPow = powerList.length - 1;
            for (int pow = 0; pow < powerList.length; pow++) {
                if (pow == maxPow) {
                    long sum = powerList[pow] + carry;
                    values[maxPow - pow] = sum == 0 ? "" : String.valueOf(sum);
                    break;
                }
                long sum = carry + (powerList[pow] % pow10BlockSize);
                carry = (powerList[pow] / pow10BlockSize) + (sum / pow10BlockSize);
                String value = String.valueOf(sum % pow10BlockSize);
                if (pow < maxPow && value.length() < blockSize) {
                    value = blockSizeZerosStr.substring(value.length()) + value;
                }
                values[maxPow - pow] = value;
            }

            String result = String.join("", values);
            long end = System.nanoTime();
            System.out.println("Sum took: " + (end - start));
            return result;
        }

        private Long[] splitToBlocks(String number) {
            long start = System.nanoTime();
            int idxAdj = number.length() % blockSize > 0 ? 0 : 1;
            Long[] blocksArray = new Long[1 - idxAdj + number.length() / blockSize];
            for (int idx = number.length(); idx > 0; idx -= blockSize) {
                int beginIndex = idx - blockSize;
                blocksArray[(idx / blockSize) - idxAdj] = Long
                        .parseLong(number.substring(beginIndex < 0 ? 0 : beginIndex, idx));
            }
            long end = System.nanoTime();
            System.out.println("Block generation took: " + (end - start));
            return blocksArray;
        }

        private int determineBlockSize(String multiplier, String multiplicand) {
            return 7;
        }
    }

    public String compute(String multiplier, String multiplicand) {
        if (multiplier.length() + multiplicand.length() <= MAX_SAFE_LENGTH) {
            return String.valueOf(Long.parseLong(multiplier) * Long.parseLong(multiplicand));
        }
        return new BlockMultiplication(multiplier, multiplicand).perform();
    }

    public static void main(String[] args) {
        String first = NumberUtils.randomNumber(10);
        String second = NumberUtils.randomNumber(10);
        new BlockLevelMultiplication().compute(first, second);
    }

}
