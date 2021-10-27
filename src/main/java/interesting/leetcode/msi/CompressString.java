package interesting.leetcode.msi;

public class CompressString {
    public int compress(char[] chars) {
        int idx = 0, lastOccIdx = 0, pos = 0;
        char current = chars[idx];
        while (lastOccIdx < chars.length) {
            if (lastOccIdx == chars.length - 1 || chars[lastOccIdx + 1] != current) {
                chars[pos++] = current;
                if (lastOccIdx > idx) {
                    char[] cA = String.valueOf(lastOccIdx - idx + 1).toCharArray();
                    System.arraycopy(chars, pos, cA, 0, cA.length);
                    pos += cA.length;
                }
                lastOccIdx++;
                if (lastOccIdx < chars.length) {
                    idx = lastOccIdx;
                    current = chars[idx];
                }
            } else if (chars[lastOccIdx + 1] == current) {
                lastOccIdx++;
            }
        }
        return pos;
    }

    public static void main(String[] args) {
        CompressString cs = new interesting.leetcode.msi.CompressString();
        System.out.println(cs.compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'}));
        System.out.println(cs.compress(new char[]{'a'}));
        System.out.println(cs.compress(new char[]{'a', 'b', 'c'}));
        System.out.println(cs.compress(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));
    }
}
