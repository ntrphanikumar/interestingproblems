package interesting.operations.multiply;

import java.math.BigInteger;
import java.text.NumberFormat;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import interesting.operations.NumberUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BlockLevelMultiplicationTest {

    private final NumberFormat format = NumberFormat.getInstance();
    private final BlockLevelMultiplication product = new BlockLevelMultiplication();

    @Test
    public void multiplication1OfNumbersOfSizeIntegerRangeEach() {
        String first = NumberUtils.randomNumber(9);
        String second = NumberUtils.randomNumber(9);
        assertOperationResults(() -> new BigInteger(first).multiply(new BigInteger(second)).toString(),
                () -> product.compute(first, second), "Int range each");
    }

    @Test
    public void multiplication2OfNumbersOfSizeHundredEach() {
        String first = NumberUtils.randomNumber(100);
        String second = NumberUtils.randomNumber(100);
        assertOperationResults(() -> new BigInteger(first).multiply(new BigInteger(second)).toString(),
                () -> product.compute(first, second), "Hundred length each");
    }

    @Test
    public void multiplication3OfNumbersOfSizeThousandEach() {
        String first = NumberUtils.randomNumber(1000);
        String second = NumberUtils.randomNumber(1000);
        assertOperationResults(() -> new BigInteger(first).multiply(new BigInteger(second)).toString(),
                () -> product.compute(first, second), "Thousand length each");
    }

    @Test
    public void multiplication4OfNumbersOfSizeTenThousandEach() {
        String first = NumberUtils.randomNumber(10000);
        String second = NumberUtils.randomNumber(10000);
        assertOperationResults(() -> new BigInteger(first).multiply(new BigInteger(second)).toString(),
                () -> product.compute(first, second), "Ten Thousand length each");
    }

    @Test
    public void multiplication5OfNumbersOfSizeHundredThousandEach() {
        String first = NumberUtils.randomNumber(100000);
        String second = NumberUtils.randomNumber(100000);
        assertOperationResults(() -> new BigInteger(first).multiply(new BigInteger(second)).toString(),
                () -> product.compute(first, second), "Hundred Thousand length each");
    }

    @Test
    public void multiplication6OfNumbersOfSizeMillionEach() {
        String first = NumberUtils.randomNumber(1000000);
        String second = NumberUtils.randomNumber(1000000);
        assertOperationResults(() -> new BigInteger(first).multiply(new BigInteger(second)).toString(),
                () -> product.compute(first, second), "Million length each");
    }
    
//    @Test
    public void multiplication7OfNumbersOfSizeTenMillionAndAMillion() {
        String first = NumberUtils.randomNumber(10000000);
        String second = NumberUtils.randomNumber(1000000);
        assertOperationResults(() -> new BigInteger(first).multiply(new BigInteger(second)).toString(),
                () -> product.compute(first, second), "Ten Million X Million length each");
    }

    private <T> void assertOperationResults(Operation<T> expected, Operation<T> actual, String operationType) {
        System.out.println("Operation type: " + operationType);
        Assert.assertEquals(print(expected, "Expected: "), print(actual, "ActualIn: "));
        System.out.println();
    }

    interface Operation<T> {
        T perform();
    }

    private <T> T print(Operation<T> operation, String tag) {
        long start = System.nanoTime();
        T result = operation.perform();
        long end = System.nanoTime();
        System.out.println(tag + format.format(end - start) + " nanos");
        return result;
    }
}
