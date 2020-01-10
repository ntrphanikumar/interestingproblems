package interesting.cartesianproduct;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Arrays.asList;
import java.util.Iterator;
import java.util.List;

public class CartesianProduct {
    
    public List<String> cartesianProduct(List<Iterator<String>> sets) {
        if(sets == null || sets.isEmpty()) {
            return Collections.emptyList();
        }
        if(sets.size() == 1) {
            List<String> list = new ArrayList<>();
            Iterator<String> iterator = sets.get(0);
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
            return list;
        }
        List<String> carterisanProduct = new ArrayList<>();
        List<String> cpSubset = cartesianProduct(sets.subList(1, sets.size()));
        Iterator<String> iterator = sets.get(0);
        while(iterator.hasNext()) {
            String next = iterator.next();
            for(String str: cpSubset) {
                carterisanProduct.add(next+str);
            }
        }
        return carterisanProduct;
    }
    
    public static void main(String[] args) {
        CartesianProduct cartesianProduct = new CartesianProduct();
        System.out.println(cartesianProduct.cartesianProduct(asList(Collections.emptyIterator())));
        System.out.println(cartesianProduct.cartesianProduct(asList(asList("a", "b", "c").iterator())));
        System.out.println(cartesianProduct.cartesianProduct(asList(asList("a", "b", "c").iterator(), asList("p", "q", "r").iterator())));
        System.out.println(cartesianProduct.cartesianProduct(asList(asList("a", "b", "c", "d", "e").iterator(), asList("p", "q", "r", "s", "t", "u").iterator(), asList("x", "y", "z").iterator())));
    }
}
