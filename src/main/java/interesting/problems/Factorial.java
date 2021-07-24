package interesting.problems;

public class Factorial {
    public static void main(String[] args) {
        System.out.println(new Factorial().factorial(10));
    }

    public long factorial(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
