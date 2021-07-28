package interesting.leetcode;

public class UglyNumber {
    public int nthUglyNumber(int n) {
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;
        int i = 0, j = 0, k = 0;
        for (int uglyIdx = 1; uglyIdx < n; uglyIdx++) {
            uglyNumbers[uglyIdx] = Math.min(Math.min(2 * uglyNumbers[i], 3 * uglyNumbers[j]), 5 * uglyNumbers[k]);
            if (uglyNumbers[uglyIdx] % 2 == 0) {
                i++;
            }
            if (uglyNumbers[uglyIdx] % 3 == 0) {
                j++;
            }
            if (uglyNumbers[uglyIdx] % 5 == 0) {
                k++;
            }
        }
        return uglyNumbers[n - 1];
    }

    public static void main(String[] args) {
        UglyNumber un = new UglyNumber();
        System.out.println(un.nthUglyNumber(4));
        System.out.println(un.nthUglyNumber(10));
        System.out.println(un.nthUglyNumber(11));
    }
}
