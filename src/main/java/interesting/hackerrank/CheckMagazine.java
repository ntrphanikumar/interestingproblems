package interesting.hackerrank;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CheckMagazine {
    public static void checkMagazine(List<String> magazine, List<String> note) {
        // Write your code here
        Map<String, List<String>> mg = magazine.stream().collect(Collectors.groupingBy(s -> s));
        for(String ns: note) {
            if(mg.getOrDefault(ns, Collections.emptyList()).isEmpty()) {
                System.out.println("No");
                return;
            }
            mg.getOrDefault(ns, Collections.emptyList()).remove(ns);
        }
        System.out.println("Yes");
    }
}
