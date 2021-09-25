package interesting.leetcode;

public class ReverseInteger {
    public static int reverse(int x) {
        if (x == Integer.MIN_VALUE || x == Integer.MAX_VALUE) {
            return 0;
        }
        String maxVal = String.valueOf(Integer.MAX_VALUE);
        String minVal = String.valueOf(Integer.MIN_VALUE).substring(1);
        boolean negative = x < 0;
        String val = String.valueOf(Math.abs(x));
        String reverse = new StringBuilder(val).reverse().toString();
        int cmp = reverse.compareTo(val);
        if (cmp > 0) {
            if (negative && reverse.compareTo(minVal) >= 0) {
                return 0;
            } else if (!negative && reverse.compareTo(maxVal) >= 0) {
                return 0;
            }
        }
        return Integer.parseInt(reverse) * (negative ? -1 : 1);
    }

    public static void main(String[] args) {
        System.out.println(reverse(Integer.MAX_VALUE));
        System.out.println(reverse(Integer.MIN_VALUE));
        System.out.println(reverse(123));
    }
}
