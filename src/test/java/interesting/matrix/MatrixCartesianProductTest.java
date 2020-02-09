package interesting.matrix;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class MatrixCartesianProductTest {

    private final MatrixCartesianProduct matrixCartesianProduct = new MatrixCartesianProduct();

    @Test
    public void cartesianProductShouldReturnEmptyListForEmptyMatrix() {
        assertEquals(Collections.emptyList(),
                matrixCartesianProduct.cartesianProduct(asList(Collections.emptyIterator())));
    }

    @Test
    public void cartesianProductShouldReturnInputMatixForSingleMatrix() {
        List<String> list = asList("a", "b", "c");
        assertEquals(list, matrixCartesianProduct.cartesianProduct(asList(list.iterator())));
    }

    @Test
    public void cartesianProductShouldReturnExpectedResultForProductOfTwoOneByThreeMatrices() {
        assertEquals(asList("ap", "aq", "ar", "bp", "bq", "br", "cp", "cq", "cr"), matrixCartesianProduct
                .cartesianProduct(asList(asList("a", "b", "c").iterator(), asList("p", "q", "r").iterator())));
    }

    @Test
    public void cartesianProductShouldReturnExpectedResultForMultipleMatricesOfVariyingColumnsForRowSizeOne() {
        assertEquals(
                asList("apx", "apy", "apz", "aqx", "aqy", "aqz", "arx", "ary", "arz", "asx", "asy", "asz", "atx", "aty",
                        "atz", "aux", "auy", "auz", "bpx", "bpy", "bpz", "bqx", "bqy", "bqz", "brx", "bry", "brz",
                        "bsx", "bsy", "bsz", "btx", "bty", "btz", "bux", "buy", "buz", "cpx", "cpy", "cpz", "cqx",
                        "cqy", "cqz", "crx", "cry", "crz", "csx", "csy", "csz", "ctx", "cty", "ctz", "cux", "cuy",
                        "cuz", "dpx", "dpy", "dpz", "dqx", "dqy", "dqz", "drx", "dry", "drz", "dsx", "dsy", "dsz",
                        "dtx", "dty", "dtz", "dux", "duy", "duz", "epx", "epy", "epz", "eqx", "eqy", "eqz", "erx",
                        "ery", "erz", "esx", "esy", "esz", "etx", "ety", "etz", "eux", "euy", "euz"),
                matrixCartesianProduct.cartesianProduct(asList(asList("a", "b", "c", "d", "e").iterator(),
                        asList("p", "q", "r", "s", "t", "u").iterator(), asList("x", "y", "z").iterator())));
    }
}
