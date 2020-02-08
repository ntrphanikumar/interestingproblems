package interesting.operations.multiply;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

import interesting.operations.NumberUtils;

public class BlockLevelMultiplicationTest {

    private final BlockLevelMultiplication product = new BlockLevelMultiplication();

    @Test
    public void testMultiplicationOfNumbersOfSizeHundredThousandEach() {
        String first = NumberUtils.randomNumber(99999);
        String second = NumberUtils.randomNumber(9999);
        assertOperationResults(() -> new BigInteger(first).multiply(new BigInteger(second)).toString(),
                () -> product.compute(first, second));
    }

    private <T> void assertOperationResults(Operation<T> expectedResultOperation, Operation<T> actualResultOperation) {
        Assert.assertEquals(printComputationTime(expectedResultOperation, "Expected"),
                printComputationTime(actualResultOperation, "Actual  "));
    }

    interface Operation<T> {
        T perform();
    }

    private <T> T printComputationTime(Operation<T> operation, String tag) {
        long start = System.nanoTime();
        T result = operation.perform();
        long end = System.nanoTime();
        System.out.println(tag + " Operation took: " + (end - start) + " nanos");
        return result;
    }
}
