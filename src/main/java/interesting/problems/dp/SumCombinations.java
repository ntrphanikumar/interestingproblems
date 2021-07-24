package interesting.problems.dp;

import java.util.*;

public class SumCombinations {
    public static Collection<List<Integer>> sumCombinations(int target) {
        Map<Integer, Collection<List<Integer>>> combinations = new HashMap<>();
        for (int i = 1; i <= target; i++) {
            combinations.put(i, new HashSet<>());
            for (int j = 1; j <= i - j; j++) {
                combinations.get(i).addAll(permute(combinations.get(j), combinations.get(i - j)));
            }
            combinations.get(i).add(Arrays.asList(i));
        }
        return combinations.get(target);
    }

    private static Collection<List<Integer>> permute(Collection<List<Integer>> first, Collection<List<Integer>> second) {
        Set<List<Integer>> combinations = new HashSet<>();
        for (List<Integer> firstComb : first) {
            for (List<Integer> secondComb : second) {
                List<Integer> combination = new ArrayList<>(firstComb);
                combination.addAll(secondComb);
                combination.sort((a, b) -> a - b);
                combinations.add(combination);
            }
        }
        return combinations;
    }

    public static void main(String[] args) {
        System.out.println(sumCombinations(5));
        System.out.println(sumCombinations(6));
    }
}
