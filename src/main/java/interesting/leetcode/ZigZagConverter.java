package interesting.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ZigZagConverter {
    public String convert(String s, int numRows) {
        // 0 1 2 3 4 5 6 7 8 9
        //
        // 0 2 4 6 8        0     6
        // 1 3 5 7 9        1   5 7
        //                  2 4   8
        // 0   4   8        3     9
        // 1 3 5 7 9
        // 2   6
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }
        int row = 0, totalRows = Math.min(numRows, s.length());
        boolean isGoingDown = false;
        List<StringBuilder> list = new ArrayList<>(totalRows);
        for (int i = 0; i < totalRows; i++) {
            list.add(new StringBuilder());
        }
        for (char c : s.toCharArray()) {
            list.get(row).append(c);
            if (row == 0 || row == numRows - 1) {
                isGoingDown = !isGoingDown;
            }
            row += isGoingDown ? 1 : -1;
        }
        return list.stream().collect(Collectors.joining());
    }

    public static void main(String[] args) {
        ZigZagConverter zzc = new ZigZagConverter();
        System.out.println(zzc.convert("PAYPALISHIRING", 3));
    }
}
