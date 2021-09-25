package interesting.g4g;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SumTree {
    class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    boolean isSumTree(Node root) {
        if (isLeaf(root)) {
            return true;
        }
        if (root.left == null) {
            return (isLeaf(root.right) ? 1 : 2) * root.right.data == root.data && isSumTree(root.right);
        }
        if (root.right == null) {
            return (isLeaf(root.left) ? 1 : 2) * root.left.data == root.data && isSumTree(root.left);
        }
        return (((isLeaf(root.right) ? 1 : 2) * root.right.data) + (((isLeaf(root.left) ? 1 : 2) * root.left.data)) == root.data) && isSumTree(root.left) && isSumTree(root.right);
    }

    boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    public static int birthdayCakeCandles(List<Integer> candles) {
        Map<Integer, List<Integer>> values =candles.stream().collect(Collectors.groupingBy(i->i));
        return values.get(values.keySet().stream().reduce((a,b)->(a>b?a:b)).get()).size();
    }

    public static String timeConversion(String s) {
        // Write your code here
        if(s == null || (!s.endsWith("AM") && !s.endsWith("PM"))) {
            return s;
        }
        if(s.endsWith("AM")) {
            if(s.startsWith("12:")) {
                return s.replaceFirst("12:", "00:").replaceFirst("AM", "");
            }
            return s.replaceFirst("AM", "");
        }
        if(s.endsWith("PM")) {
            if(s.startsWith("12:")) {
                return s.replaceFirst("PM", "");
            }
            String[] split = s.split(":", 2);
            return (Integer.parseInt(split[0])+12)+":"+split[1].replaceFirst("PM", "");
        }
        return s;
    }

}
