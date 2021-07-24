package interesting.operations.multiply;

import interesting.operations.NumberUtils;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    /*
     * handle carry and remove the starting zeros of the number as those are not
     * required
     */
    static Function<Integer[], String> productFunction = result -> {
        StringBuilder product = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            int modulus = result[i] % 10;
            int carry = result[i] / 10;
            if (i + 1 < result.length) {
                result[i + 1] = result[i + 1] + carry;
            }
            product.insert(0, modulus);
        }
        // used regex to remove leading zeros before returning the actual product
        return product.toString().replaceFirst("^0+(?!$)", "");
    };
    // lamda expression to return the reverse of a string
    static Function<String, String> reverseFunction = number -> new StringBuilder(number).reverse().toString();
    // lamda expression to compute length of given string
    static Function<String, Integer> lengthFunction = number -> number.length();
    static Predicate<String> isPositivePredicate = number -> number.contains("-");

    public static void main(String[] args) {
//      System.out.print("Enter first number:");
//      String s1= new Scanner(System.in).nextLine();
//      System.out.print("Enter second number: ");
//      String s2= new Scanner(System.in).nextLine();
//
//      System.out.println("Product of two numbers is:"+multiplyTwoNumbers(s1,s2));

        String first = NumberUtils.randomNumber(100000);
        String second = NumberUtils.randomNumber(100000);
        String result = new BlockLevelMultiplication(true).compute(first, second);
//        System.out.println(first);
//        System.out.println(second);
//        System.out.println(result);
        System.out.println(result.equals(multiplyTwoNumbers(first, second)));
    }

    public static String multiplyTwoNumbers(String number1, String number2) {
        if (isPositivePredicate.test(number1) || isPositivePredicate.test(number2)) {
            return "Invalid as input numbers should be positive.please check and try again later";
        }
        String s1 = reverseFunction.apply(number1);
        String s2 = reverseFunction.apply(number2);
        int l1 = lengthFunction.apply(number1);
        int l2 = lengthFunction.apply(number2);

        int[] result = new int[l1 + l2];

        // to compute product manually for each digit of the given number
        computeProductForeachRow(s1, s2, result);

        return productFunction.apply(Arrays.stream(result).boxed().toArray(Integer[]::new));

    }

    /*
     * to compute product manually for each digit of the given number
     */
    public static void computeProductForeachRow(String s1, String s2, int[] result) {
        for (int i = 0; i < lengthFunction.apply(s1); i++) {
            for (int j = 0; j < lengthFunction.apply(s2); j++) {
                result[i + j] = result[i + j] + (s1.charAt(i) - '0') * (s2.charAt(j) - '0');
            }
        }
    }

}