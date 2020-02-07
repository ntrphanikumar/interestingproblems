package interesting.operations.largenumbers;

import java.math.BigInteger;

public class ProductPerformanceTest {

    public static void main(String[] args) {
        String first = NumberUtils.randomNumber(999);
        String second = NumberUtils.randomNumber(99);

        System.out.println(first);
        System.out.println("X");
        System.out.println(second);

        System.out.println("BigInt Computation");
        long start = System.nanoTime();
        String bigIntProduct = new BigInteger(first).multiply(new BigInteger(second)).toString();
        long end = System.nanoTime();
        System.out.println("Product computed in " + (end - start) + " nanos");
        System.out.println(bigIntProduct);
        System.out.println();

        System.out.println("SingleDigit Computation");
        start = System.nanoTime();
        String singleDigitProduct = new SingleDigitProduct().compute(first, second);
        end = System.nanoTime();
        System.out.println("Product computed in " + (end - start) + " nanos");
        System.out.println(singleDigitProduct);
        System.out.println();

        System.out.println("GradeSchool Computation");
        start = System.nanoTime();
        String gradeSchoolProduct = new GradeSchoolProduct().compute(first, second);
        end = System.nanoTime();
        System.out.println("Product computed in " + (end - start) + " nanos");
        System.out.println(gradeSchoolProduct);
        System.out.println();
    }
}
