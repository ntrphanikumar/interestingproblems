package interesting.leetcode.msi;

import java.util.HashSet;
import java.util.Set;

public class MinAdjacentSwapsToMakePalindrome {
    public int minSwaps(String s) {
        if (s == null || s.length() == 0 || !canBePalindrome(s)) {
            return -1;
        }
        int swaps = 0;
        char[] chars = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (chars[i] == chars[j]) {
                i++;
                j--;
                continue;
            }
            int k = j;
            while (k > i && chars[k] != chars[i]) {
                k--;
            }
            if (k == i) {//Single odd char
                swap(chars, i, i + 1);
                swaps++;
            } else { // Move char from pos k to j
                while (k < j) {
                    swap(chars, k, k + 1);
                    k++;
                    swaps++;
                }
                i++;
                j--;
            }
        }
        return swaps;
    }

    private void swap(char[] chars, int x, int y) {
        char c = chars[y];
        chars[y] = chars[x];
        chars[x] = c;
    }

    private boolean canBePalindrome(String s) {
        Set<Character> oddChars = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (oddChars.contains(c)) {
                oddChars.remove(c);
            } else {
                oddChars.add(c);
            }
        }
        return s.length() % 2 == 0 ? oddChars.isEmpty() : oddChars.size() == 1;
    }

    public static void main(String[] args) {
        MinAdjacentSwapsToMakePalindrome mas = new MinAdjacentSwapsToMakePalindrome();
        System.out.println(mas.minSwaps("abcd"));
        System.out.println(mas.minSwaps("ababccd"));
    }
}
