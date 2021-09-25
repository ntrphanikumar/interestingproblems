package interesting.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class LetterCombination {
    private static final Map<Character, List<Character>> CHAR_BY_DIGIT = new HashMap<>();

    static {
        CHAR_BY_DIGIT.put('2', List.of('a', 'b', 'c'));
        CHAR_BY_DIGIT.put('3', List.of('d', 'e', 'f'));
        CHAR_BY_DIGIT.put('4', List.of('g', 'h', 'i'));
        CHAR_BY_DIGIT.put('5', List.of('j', 'k', 'l'));
        CHAR_BY_DIGIT.put('6', List.of('m', 'n', 'o'));
        CHAR_BY_DIGIT.put('7', List.of('p', 'q', 'r', 's'));
        CHAR_BY_DIGIT.put('8', List.of('t', 'u', 'v'));
        CHAR_BY_DIGIT.put('9', List.of('w', 'x', 'y', 'z'));
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }
        if (digits.length() == 1) {
            return CHAR_BY_DIGIT.get(digits.charAt(0)).stream().map(String::valueOf).collect(Collectors.toList());
        }
        List<String> combinationsTillNow = letterCombinations(digits.substring(1));
        List<Character> charForDigit = CHAR_BY_DIGIT.get(digits.charAt(0));
        List<String> results = new ArrayList<>(charForDigit.size() * combinationsTillNow.size());
        charForDigit.forEach(c -> combinationsTillNow.forEach(comb ->results.add(c + comb)));
        return results;
    }

    public static void main(String[] args) {
        LetterCombination lc = new LetterCombination();
        System.out.println(lc.letterCombinations("23"));
    }
}
