package interesting.dsalgos.sort;

import interesting.dsalgos.ds.Heap;

import java.util.Arrays;

import static interesting.dsalgos.sort.Utils.print;

public class HeapSort<T extends Comparable<T>> {
    public void sort(T[] unsortedArray) {
        Heap<T> heap = Heap.minHeap(unsortedArray.length);
        Arrays.stream(unsortedArray).forEach(heap::add);
        for (int idx = 0; idx < unsortedArray.length; idx++) {
            unsortedArray[idx] = heap.poll();
        }
    }

    public static void main(String[] args) {
        Integer[] unsortedArr = {5, 3, 12, -10, 8, 10, -6};
        System.out.print("Input: ");
        print(unsortedArr);
        new HeapSort<Integer>().sort(unsortedArr);
        System.out.println("Result: ");
        print(unsortedArr);
    }
}
