package interesting.iprep.ggl;

import java.util.PriorityQueue;

public class OrderStatistics {

    public static int nthMinimum(int[] numbers, int m) {
        if (numbers.length < m || m <= 0) {
            throw new IllegalArgumentException();
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(m, (a, b) -> b - a);
        for (int number : numbers) {
            if (maxHeap.size() < m) {
                maxHeap.add(number);
            } else {
                if (maxHeap.peek() > number) {
                    maxHeap.poll();
                    maxHeap.add(number);
                }
            }
        }
        System.out.println(maxHeap);
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        int[] numbers = {3, 1, 7, 4, 9, 0};
        System.out.println(nthMinimum(numbers,1));
        System.out.println(nthMinimum(numbers,2));
        System.out.println(nthMinimum(numbers,3));
        System.out.println(nthMinimum(numbers,4));
        System.out.println(nthMinimum(numbers,5));
        System.out.println(nthMinimum(numbers,6));
    }
}
