package interesting.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MatrixCartesianProduct {

    /**
     * Computes Cartesian product of matrices
     *
     * @param sets
     * @return
     */
    public List<String> cartesianProduct(List<Iterator<String>> sets) {
        if (sets == null || sets.isEmpty()) {
            return Collections.emptyList();
        }
        if (sets.size() == 1) {
            List<String> list = new ArrayList<>();
            sets.get(0).forEachRemaining(list::add);
            return list;
        }
        List<String> cpSubset = cartesianProduct(sets.subList(1, sets.size()));
        List<String> carterisanProduct = new ArrayList<>();
        sets.get(0).forEachRemaining(ele -> cpSubset.stream().map(str -> ele + str).forEach(carterisanProduct::add));
        return carterisanProduct;
    }
}
