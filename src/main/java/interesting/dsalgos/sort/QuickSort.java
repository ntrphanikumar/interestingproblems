package interesting.dsalgos.sort;

import static interesting.dsalgos.sort.Utils.print;

public class QuickSort<T extends Comparable> {
    public static void main(String[] args) {
        Integer[] unsortedArr = {5, 3, 12, -10, 8, 10, -6};
        System.out.print("Input: ");
        print(unsortedArr);
        new QuickSort<>().sort(unsortedArr);
        System.out.print("Result: ");
        print(unsortedArr);
    }

    public void sort(T[] array) {
        quickSort(array, 0, array.length);
    }

    private void quickSort(T[] array, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int partitionIdx = partition(array, start, end);
        quickSort(array, start, partitionIdx);
        quickSort(array, partitionIdx + 1, end);
    }

    private int partition(T[] array, int start, int end) {
        T pivot = array[start];
        int i = start;
        int j = end;
        while (i < j) {
            while (i < j && pivot.compareTo(array[--j]) < 0) ;
            if (i < j) {
                array[i] = array[j];
            }
            while (i < j && pivot.compareTo(array[++i]) > 0) ;
            if (i < j) {
                array[j] = array[i];
            }
        }
        array[j] = pivot;
        return j;
    }

}
