package interesting.leetcode.msi;

import java.util.PriorityQueue;
import java.util.Queue;

public class LargestNumberAndItsNegative {
    public static int largestK(int[] numbers) {
        if (numbers == null || numbers.length <= 1) {
            return 0;
        }
        Queue<Integer> minHeap = new PriorityQueue<>();
        Queue<Integer> maxHeap = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
        for (int n : numbers) {
            if (n > 0) {
                maxHeap.add(n);
            } else if (n < 0) {
                minHeap.add(n);
            }
        }
        while (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
            if (minHeap.peek() == -maxHeap.peek()) {
                return maxHeap.peek();
            }
            if (-minHeap.peek() > maxHeap.peek()) {
                minHeap.poll();
            } else {
                maxHeap.poll();
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(largestK(new int[]{3, 2, -2, 5, -3}));
        System.out.println(largestK(new int[]{1, 2, 3, -4}));
    }
}
