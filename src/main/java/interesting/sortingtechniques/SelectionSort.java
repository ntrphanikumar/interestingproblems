package interesting.sortingtechniques;

import static java.util.Arrays.asList;

public class SelectionSort implements SortingTechnique {
    
    @Override
    public Integer[] sort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            swapIdxVals(arr, i, indexOfMin(arr, i));
        }
        return arr;
    }

    private int indexOfMin(Integer[] arr, int arrStartIdx) {
        int minValIdx = arrStartIdx;
        for (int i = arrStartIdx + 1; i < arr.length; i++) {
            if (arr[i] < arr[minValIdx]) {
                minValIdx = i;
            }
        }
        return minValIdx;
    }

    public static void main(String[] args) {
        Integer[] unsorted = { 64, 25, 12, 22, 11 };
        System.out.println(asList(unsorted));
        System.out.println(asList(new SelectionSort().sort(unsorted)));
    }
}
