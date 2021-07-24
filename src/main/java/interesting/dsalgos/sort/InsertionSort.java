package interesting.dsalgos.sort;

import static interesting.dsalgos.sort.Utils.print;

public class InsertionSort<T extends Comparable> {
    public static void main(String[] args) {
        Integer[] unsortedArr = {5, 3, 12, -10, 8, 10, -6};
        System.out.print("Input: ");
        print(unsortedArr);
        new InsertionSort<>().sort(unsortedArr);
        System.out.print("Result: ");
        print(unsortedArr);
    }

    public void sort(T[] array) {
        for (int firstUnsortedIdx = 1; firstUnsortedIdx < array.length; firstUnsortedIdx++) {
            T newValue = array[firstUnsortedIdx];
            int i = firstUnsortedIdx;
            while (i > 0 && newValue.compareTo(array[i - 1]) < 0) {
                array[i] = array[i - 1];
                i--;
            }
            array[i] = newValue;
            print(array);
        }
    }
}
