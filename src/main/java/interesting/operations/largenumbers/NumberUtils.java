package interesting.operations.largenumbers;

import java.util.Random;

public class NumberUtils {
    public static String randomNumber(int size) {
        int leftLimit = '0', rightLimit = '9';
        return new Random().ints(leftLimit, rightLimit + 1).limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    public static void main(String[] args) {
        System.out.println(randomNumber(20));
    }
}
