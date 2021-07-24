package interesting.dsalgos.sort;

import static interesting.dsalgos.sort.Utils.print;

public class MergeSort<T extends Comparable> {
    public static void main(String[] args) {
        Integer[] unsortedArr = {5, 3, 12, -10, 8, 10, -6};
        System.out.print("Input: ");
        print(unsortedArr);
        new MergeSort<>().sort(unsortedArr);
        System.out.print("Result: ");
        print(unsortedArr);
    }

    public void sort(T[] array) {
        mergeSort(array, 0, array.length);
    }

    private void mergeSort(T[] array, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(array, start, mid);
        mergeSort(array, mid, end);
        merge(array, start, mid, end);
    }

    private void merge(T[] array, int start, int mid, int end) {
        if (array[mid - 1].compareTo(array[mid]) < 1) {
            return;
        }
        Object[] temp = new Object[end - start];
        int i = start, j = mid, tempIdx = 0;
        while (i < mid && j < end) {
            temp[tempIdx++] = (array[i].compareTo(array[j]) < 1) ? array[i++] : array[j++];
        }
        System.arraycopy(array, i, temp, tempIdx, mid - i);
        System.arraycopy(temp, 0, array, start, j - start);
    }

}
