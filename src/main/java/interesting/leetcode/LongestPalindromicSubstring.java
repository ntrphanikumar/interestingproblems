package interesting.leetcode;

public class LongestPalindromicSubstring {

    /**
     * A palindrome's center can be a character or between 2 characters.
     * So total possible centers for palindrome of string length n are n+(n-1)
     * <p>
     * For each center position identify the longest palindrome.
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        String longestPalindrome = "";
        for (int i = 0; i < chars.length; i++) {
            String pal = longestPalindrome(s, chars, i, i);
            if (pal.length() > longestPalindrome.length()) {
                longestPalindrome = pal;
            }
            if (i < chars.length - 1) {
                String palNew = longestPalindrome(s, chars, i, i + 1);
                if (palNew.length() > longestPalindrome.length()) {
                    longestPalindrome = palNew;
                }
            }
        }
        return longestPalindrome;
    }

    private String longestPalindrome(String s, char[] chars, int centerLeftIdx, int centerRightIdx) {
        int palStartIdx = -1, palEndIdx = -1;
        if (centerLeftIdx == centerRightIdx) {
            palStartIdx = palEndIdx = centerLeftIdx;
            centerLeftIdx--;
            centerRightIdx++;
        }
        for (int left = centerLeftIdx, right = centerRightIdx; left >= 0 && right < chars.length; left--, right++) {
            if (chars[left] == chars[right]) {
                palStartIdx = left;
                palEndIdx = right;
            } else {
                break;
            }
        }

        return palStartIdx == -1 ? "" : s.substring(palStartIdx, palEndIdx + 1);
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
        System.out.println(lps.longestPalindrome("babad"));
        System.out.println(lps.longestPalindrome("cbbd"));
        System.out.println(lps.longestPalindrome("a"));
        System.out.println(lps.longestPalindrome("ac"));
    }
}
