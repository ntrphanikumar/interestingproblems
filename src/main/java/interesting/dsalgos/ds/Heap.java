package interesting.dsalgos.ds;

import java.util.Arrays;
import java.util.Comparator;

public class Heap<T extends Comparable<T>> {
    private Object[] heap;
    private int size;
    private final Comparator<T> comparator;

    private Heap(int capacity) {
        this(capacity, Comparator.comparing(t -> t));
    }

    private Heap(int capacity, Comparator<T> comparator) {
        heap = new Object[capacity];
        this.comparator = comparator;
    }

    public static <R extends Comparable<R>> Heap<R> minHeap(int capacity) {
        return new Heap<>(capacity);
    }

    public static <R extends Comparable<R>> Heap<R> maxHeap(int capacity) {
        Comparator<R> comparator = Comparator.comparing(t -> t);
        return new Heap<>(capacity, comparator.reversed());
    }

    public void add(T number) {
        if (size == heap.length) {
            Object[] newHeap = new Object[2 * size];
            System.arraycopy(heap, 0, newHeap, 0, size);
            heap = newHeap;
        }
        heap[size++] = number;
        heapify(size - 1);
    }

    public T peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Nothing to peek");
        }
        return value(0);
    }

    public T poll() {
        T num = peek();
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
        Object temp = heap[idx1];
        heap[idx1] = heap[idx2];
        heap[idx2] = temp;
    }

    private void heapify(int idx) {
        int parentIdx = (idx - 1) / 2, leftChildIdx = 2 * idx + 1, rightChildIdx = leftChildIdx + 1;
        if (idx > 0 && comparator.compare(value(parentIdx), value(idx)) > 0) {
            swap(idx, parentIdx);
            heapify(parentIdx);
        }
        if (size > leftChildIdx && comparator.compare(value(leftChildIdx), value(idx)) < 0) {
            swap(idx, leftChildIdx);
            heapify(leftChildIdx);
        }
        if (size > rightChildIdx && comparator.compare(value(rightChildIdx), value(idx)) < 0) {
            swap(idx, rightChildIdx);
            heapify(rightChildIdx);
        }
    }

    private T value(int idx) {
        return (T) heap[idx];
    }

    public void print() {
        System.out.println(Arrays.asList(heap).subList(0, size));
    }

    public static void main(String[] args) {
        Heap<Integer> minHeap = Heap.minHeap(10);
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
}
