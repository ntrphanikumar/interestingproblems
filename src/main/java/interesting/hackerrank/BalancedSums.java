package interesting.hackerrank;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BalancedSums {

    public static String balancedSums(List<Integer> arr) {
        int leftIdx = -1, rightIdx = arr.size();
        long leftSum = 0, rightSum = 0;
        while (leftIdx < rightIdx) {
            if (leftSum < rightSum) {
                leftSum += arr.get(++leftIdx);
            } else if (leftSum > rightSum) {
                rightSum += arr.get(--rightIdx);
            } else if (rightIdx - leftIdx > 1) {
                leftSum += arr.get(++leftIdx);
                rightSum += arr.get(--rightIdx);
            } else {
                break;
            }
        }
        if (leftIdx == rightIdx && leftSum == rightSum) {
            return "YES";
        }
        return "NO";
    }

    public static List<Integer> gradingStudents(List<Integer> grades) {
        return grades.stream().map(grade -> grade + ((grade > 37 && grade % 5 >= 3) ? (5 - (grade % 5)) : 0)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
//        System.out.println(balancedSums(Arrays.asList(1)));
//        System.out.println(balancedSums(Arrays.asList(2,0)));
//        System.out.println(balancedSums(Arrays.asList(0,2)));
//        System.out.println(balancedSums(Arrays.asList(1,2)));
//        System.out.println(balancedSums(Arrays.asList(1,1,1)));
        System.out.println(balancedSums(Arrays.asList(1, 1, 2)));

        System.out.println(gradingStudents(Arrays.asList(37, 38, 41, 58, 57)));
    }
}
