package interesting.operations;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MultiThreading {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> helloCF = CompletableFuture.supplyAsync(() -> "Hello");

        System.out.println(helloCF
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World")).get());

        System.out.println(helloCF
                .thenCombine(CompletableFuture.supplyAsync(
                        () -> " World"), (s1, s2) -> s1 + s2).get());

        helloCF.thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"),
                (s1, s2) -> System.out.println(s1 + s2));

        System.out.println(gcd(5, 15));
        System.out.println(gcd(15, 75));
        System.out.println(gcd(5, 8));
    }

    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
        // Write your code here
        Map<String, List<String>> grouped = strings.stream().collect(Collectors.groupingBy(Function.identity()));
        return queries.stream().map(q -> grouped.getOrDefault(q, Collections.emptyList()).size()).collect(Collectors.toList());
    }

    public static int getTotalX(List<Integer> a, List<Integer> b) {
        // Write your code here
        Collections.sort(a);
        Collections.sort(b);
        Integer lcm = a.stream().reduce((p, q) -> p * q / gcd(p, q)).get();
        Integer gcd = b.stream().reduce(MultiThreading::gcd).get();
        System.out.println(lcm + " " + gcd);
        int count = 0;
        for (int i = 0; i < gcd / lcm; i++) {
//            if()
        }
        return gcd / lcm;
    }

    private static Integer gcd(Integer a, Integer b) {
        return b % a == 0 ? a : gcd(b % a, a);
    }
}
