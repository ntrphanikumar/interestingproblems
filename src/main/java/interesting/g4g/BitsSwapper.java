package interesting.g4g;

public class BitsSwapper {
    public static int swapBits(int n) {
        printBin(n);
        int allOdd = 0x55555555 >> 1;
        int allEven = 0x55555555;
        int x = n & allOdd;
        int y = n & allEven;
        printBin(x);
        printBin(y);
        printBin(x >> 1);
        printBin(y << 1);
        return ((n & (0x55555555 >> 1)) >> 1) | ((n & (0x55555555)) << 1);
    }

    private static void printBin(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % 2);
            n >>= 1;
        }
        System.out.println(sb.reverse().toString());
    }

    public static void main(String[] args) {
        printBin(swapBits(23));
    }
}
