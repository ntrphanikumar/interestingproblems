package interesting.problems.apple.heaps;

import java.util.Arrays;

public class MaxHeap {
    private final Integer[] heap;
    private int size;

    MaxHeap(int capacity) {
        heap = new Integer[capacity];
    }

    public void add(int number) {
        if (size == heap.length) {
            throw new IndexOutOfBoundsException("Heap is full");
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
        int parentIdx = parent(idx), leftChildIdx = leftChild(idx), rightChildIdx = rightChild(idx);
        if (idx > 0 && heap[parentIdx] < heap[idx]) {
            swap(idx, parentIdx);
            heapify(parentIdx);
        }
        if (size > leftChildIdx && heap[leftChildIdx] > heap[idx]) {
            swap(idx, leftChildIdx);
            heapify(leftChildIdx);
        }
        if (size > rightChildIdx && heap[rightChildIdx] > heap[idx]) {
            swap(idx, rightChildIdx);
            heapify(rightChildIdx);
        }
    }

    private int leftChild(int idx) {
        return 2 * idx + 1;
    }

    private int rightChild(int idx) {
        return 2 * idx + 2;
    }

    private int parent(int idx) {
        return (idx - 1) / 2;
    }

    public void print() {
        System.out.println(Arrays.asList(heap));
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(10);
        for (int i = 10; i > 0; i--) {
            maxHeap.add(i);
            maxHeap.print();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(maxHeap.poll());
            maxHeap.print();
        }

        maxHeap.add(5);
        maxHeap.print();
    }
}
