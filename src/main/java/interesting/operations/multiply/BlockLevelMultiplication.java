package interesting.operations.multiply;

import interesting.operations.NumberUtils;

import java.text.NumberFormat;

public class BlockLevelMultiplication {

    private final boolean printLogs;

    public BlockLevelMultiplication() {
        this(false);
    }

    public BlockLevelMultiplication(boolean printLogs) {
        this.printLogs = printLogs;
    }

    public static void main(String[] args) {
        String first = NumberUtils.randomNumber(1000000);
        String second = NumberUtils.randomNumber(1000000);
        new BlockLevelMultiplication(true).compute(first, second);
    }

    public String compute(String first, String second) {
        long start = System.nanoTime();
        final int longRange = (Long.MAX_VALUE + "").length() - 1;
        if (first.length() + second.length() <= longRange) {
            return String.valueOf(Long.parseLong(first) * Long.parseLong(second));
        }
        int blockSize = 7;
        String res = sum(powerMap(splitToBlocks(first, blockSize), splitToBlocks(second, blockSize)), blockSize);
        logTime("Total multiplication", start, System.nanoTime());
        return res;
    }

    private long[] powerMap(long[] multiplier, long[] multiplicand) {
        long start = System.nanoTime();
        int totalBlocksLength = multiplier.length + multiplicand.length;
        long[] values = new long[totalBlocksLength - 1];
        for (int i = 0; i < multiplier.length; i++) {
            for (int j = 0; j < multiplicand.length; j++) {
                values[totalBlocksLength - i - j - 2] += multiplicand[j] * multiplier[i];
            }
        }
        logTime("Power list generation", start, System.nanoTime());
        return values;
    }

    private String sum(long[] numbers, int blockSize) {
        long start = System.nanoTime();
        final long pow10BlockSize = (long) Math.pow(10d, blockSize);
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
        logTime("Sum", start, System.nanoTime());
        return result;
    }

    private long[] splitToBlocks(String number, int blockSize) {
        long start = System.nanoTime();
        int idxAdj = number.length() % blockSize > 0 ? 0 : 1;
        long[] blocksArray = new long[1 - idxAdj + number.length() / blockSize];
        for (int idx = number.length(); idx > 0; idx -= blockSize) {
            int beginIndex = idx - blockSize;
            blocksArray[(idx / blockSize) - idxAdj] = Long
                    .parseLong(number.substring(beginIndex < 0 ? 0 : beginIndex, idx));
        }
        logTime("Block generation", start, System.nanoTime());
        return blocksArray;
    }

    private void logTime(String operation, long start, long end) {
        if (printLogs) {
            System.out.println(operation + " took: " + NumberFormat.getInstance().format(end - start) + " nanos");
        }
    }

}
