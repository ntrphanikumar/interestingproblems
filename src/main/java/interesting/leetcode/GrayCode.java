package interesting.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GrayCode {

    public List<Integer> grayCode(int n) {
        List<Integer> codes = new ArrayList<>();
        codes.add(0);
        codes.add(1);
        for (int i = 1; i < n; i++) {
            int size = codes.size();
            int leftOne = 1 << i;
            for (int j = size - 1; j >= 0; j--) {
                codes.add(codes.get(j) | leftOne);
            }
        }
        return codes;
    }

    private class Version2 {
        public List<Integer> grayCode(int n) {
            return grayCode(n, new ArrayList<>((int) Math.pow(2, n)));
        }

        public List<Integer> grayCode(int n, List<Integer> codes) {
            if (n == 1) {
                codes.add(0);
                codes.add(1);
                return codes;
            }
            int pow = (int) Math.pow(2d, new Double(n - 1));
            for (int i = grayCode(n - 1, codes).size(); i > 0; ) {
                codes.add(pow + codes.get(--i));
            }
            return codes;
        }
    }

    private class Version1 {
        public List<Integer> grayCode(int n) {
            return Arrays.<String>asList(grayCodes(n)).stream().map(s -> Integer.parseInt(s, 2)).collect(Collectors.toList());
        }

        private String[] grayCodes(int n) {
            if (n == 1) {
                return new String[]{"0", "1"};
            }
            String[] prevCodes = grayCodes(n - 1);
            String[] codes = new String[2 * prevCodes.length];
            int i = 0;
            for (String code : prevCodes) {
                codes[i] = "0" + code;
                codes[codes.length - (++i)] = "1" + code;
            }
            return codes;
        }
    }

    public static void main(String[] args) {
        GrayCode gc = new GrayCode();

        System.out.println(gc.grayCode(2));
    }
}
