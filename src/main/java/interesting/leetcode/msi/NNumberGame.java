package interesting.leetcode.msi;

public class NNumberGame {
    public static String message(String s, int[] arr) {
        if (s == null || s.length() == 0 || s.length() != arr.length) {
            return "";
        }
        char[] cArr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int nextPos = 0;
        do {
            sb.append(cArr[nextPos]);
            nextPos = arr[nextPos];
        } while (nextPos > 0);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(message("cdeo", new int[]{3, 2, 0, 1}));
        System.out.println(message("cdeenetpi", new int[]{5, 2, 0, 1, 6, 4, 8, 3, 7}));
        System.out.println(message("bytdag", new int[]{4, 3, 0, 1, 2, 5}));
    }
}
