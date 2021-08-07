package interesting.problems.heaps;

import interesting.dsalgos.ds.Heap;

public class IntegerStreamMedian {
    private final Heap<Integer> maxHeap = Heap.maxHeap(10);
    private final Heap<Integer> minHeap = Heap.minHeap(10);

    public static void main(String[] args) {
        IntegerStreamMedian ism = new IntegerStreamMedian();
        for (int num : new int[]{5, 15, 1, 3}) {
            System.out.println(ism.addNumberAndGetMedian(num));
        }
    }

    public int addNumberAndGetMedian(int num) {
        if (minHeap.isEmpty() || minHeap.peek() < num) {
            minHeap.add(num);
        } else {
            maxHeap.add(num);
        }
        equalize();
        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek()) / 2;
        }
        return minHeap.size() > maxHeap.size() ? minHeap.peek() : maxHeap.peek();
    }

    private void equalize() {
        while (Math.abs(minHeap.size() - maxHeap.size()) > 1) {
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            } else {
                minHeap.add(maxHeap.poll());
            }
        }
    }
}
