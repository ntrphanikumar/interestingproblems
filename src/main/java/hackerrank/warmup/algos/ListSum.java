package hackerrank.warmup.algos;

public class ListSum {
    public static int simpleArraySum(java.util.List<Integer> ar) {
        // Write your code here
        return ar.stream().reduce((a, b) -> a+b).get();
    }

    public static void main(String[] args) {
        System.out.println(simpleArraySum(java.util.Arrays.asList(5, 4, 7)));
    }
}
