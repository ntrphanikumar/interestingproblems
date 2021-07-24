package interesting.dsalgos.ds;

import java.util.Arrays;
import java.util.Comparator;

public class Heap {
    private final Comparator<Integer> comparator;
    private Integer[] heap;
    private int size;

    private Heap(int capacity, Comparator<Integer> comparator) {
        heap = new Integer[capacity];
        this.comparator = comparator;
    }

    public static Heap minHeap(int capacity) {
        return new Heap(capacity, Comparator.comparingInt(a -> a));
    }

    public static Heap maxHeap(int capacity) {
        return new Heap(capacity, Comparator.comparingInt(a -> -a));
    }

    public static void main(String[] args) {
        Heap minHeap = Heap.minHeap(10);
        for (int i = 20; i > 0; i--) {
            minHeap.add(i);
            minHeap.print();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(minHeap.poll());
            minHeap.print();
        }

        minHeap.add(5);
        minHeap.print();
    }

    public void add(int number) {
        if (size == heap.length) {
            Integer[] newHeap = new Integer[2 * size];
            System.arraycopy(heap, 0, newHeap, 0, size);
            heap = newHeap;
        }
        heap[size++] = number;
        heapify(size - 1);
    }

    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Nothing to peek");
        }
        return heap[0];
    }

    public int poll() {
        int num = peek();
        heap[0] = heap[--size];
        heap[size] = null;
        heapify(0);
        return num;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    private void swap(int idx1, int idx2) {
        int temp = heap[idx1];
        heap[idx1] = heap[idx2];
        heap[idx2] = temp;
    }

    private void heapify(int idx) {
        int parentIdx = (idx - 1) / 2, leftChildIdx = 2 * idx + 1, rightChildIdx = leftChildIdx + 1;
        if (idx > 0 && comparator.compare(heap[parentIdx], heap[idx]) > 0) {
            swap(idx, parentIdx);
            heapify(parentIdx);
        }
        if (size > leftChildIdx && comparator.compare(heap[leftChildIdx], heap[idx]) < 0) {
            swap(idx, leftChildIdx);
            heapify(leftChildIdx);
        }
        if (size > rightChildIdx && comparator.compare(heap[rightChildIdx], heap[idx]) < 0) {
            swap(idx, rightChildIdx);
            heapify(rightChildIdx);
        }
    }

    public void print() {
        System.out.println(Arrays.asList(heap).subList(0, size));
    }
}
