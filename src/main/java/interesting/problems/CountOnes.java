package interesting.problems;

public class CountOnes {
    public static int ones(int number) {
        int mask = 0b1;
        int ones = 0;
        while (number > 0) {
            ones += number & mask;
            number >>= 1;
        }
        return ones;
    }

    public static void main(String[] args) {
        for(int i=0;i<11;i++) {
            System.out.println(ones(i));
        }
    }
}
