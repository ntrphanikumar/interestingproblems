package interesting.operations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Test implements Runnable {
    @Override
    public void run() {
        System.out.println("Turing ");
    }

    public static int[] rotate(int[] nums, int k) {
        int[] result = nums;
        int temp = 0;
        for (int i = k; i < nums.length + k; i++) {
                temp = result[i-k];
                result[i % nums.length] = temp;
                temp = result[i-k];
        }
        return result;
    }

    private static void print(int[] arr) {
        for(int a: arr) {
            System.out.print(a);
        }
        System.out.println();
    }


    public static void main(String[] args) {
//        print(rotate(new int[]{1, 2, 3, 4}, 0));
//        print(rotate(new int[]{1, 2, 3, 4}, 1));
//        print(rotate(new int[]{1, 2, 3, 4}, 2));
//        print(rotate(new int[]{1, 2, 3, 4}, 3));
//        print(rotate(new int[]{1, 2, 3, 4}, 4));
//        print(rotate(new int[]{1, 2, 3, 4}, 5));

        int x = 0x10;
        System.out.println(x);
    }
}
