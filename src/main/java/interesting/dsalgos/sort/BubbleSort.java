package interesting.dsalgos.sort;

import static interesting.dsalgos.sort.Utils.print;
import static interesting.dsalgos.sort.Utils.swap;

public class BubbleSort<T extends Comparable> {
    public static void main(String[] args) {
        Integer[] unsortedArr = {5, 3, 12, -10, 8, 10, -6};
        System.out.print("Input: ");
        print(unsortedArr);
        new BubbleSort<>().sort(unsortedArr);
        System.out.println("Result: ");
        print(unsortedArr);
    }

    public void sort(T[] unsortedArray) {
        for (int i = 0; i < unsortedArray.length - 1; i++) {
            for (int j = 0; j < unsortedArray.length - i - 1; j++) {
                if (unsortedArray[j].compareTo(unsortedArray[j + 1]) > 0) {
                    swap(unsortedArray, j, j + 1);
                }
            }
            print(unsortedArray);
        }
    }
}
