package interesting.dsalgos.sort;

import static interesting.dsalgos.sort.Utils.print;

public class ShellSort<T extends Comparable> {
    public static void main(String[] args) {
        Integer[] unsortedArr = {5, 3, 12, -10, 8, 10, -6};
        System.out.print("Input: ");
        print(unsortedArr);
        new ShellSort<>().sort(unsortedArr);
        System.out.print("Result: ");
        print(unsortedArr);
    }

    public void sort(T[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            sortWithGap(array, gap);
        }
    }

    private void sortWithGap(T[] array, int gap) {
        if (gap < 1) {
            return;
        }
        System.out.println("Sort with gap: " + gap);
        for (int firstUnsortedIdx = gap; firstUnsortedIdx < array.length; firstUnsortedIdx++) {
            T newValue = array[firstUnsortedIdx];
            int i = firstUnsortedIdx;
            while (i >= gap && newValue.compareTo(array[i - gap]) < 0) {
                array[i] = array[i - gap];
                i -= gap;
            }
            array[i] = newValue;
            print(array);
        }
    }
}
