package interesting.sortingtechniques;

import static java.util.Arrays.asList;

public class InsertionSort implements SortingTechnique {

    @Override
    public Integer[] sort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr = insertElementAtAppropriatePositionBefore(arr, i);
        }
        return arr;
    }

    private Integer[] insertElementAtAppropriatePositionBefore(Integer[] arr, int idx) {
        int indexToInsertAt = indexToInsertAt(arr, 0, idx - 1, arr[idx]);
        if (indexToInsertAt < idx) {
            Integer valToInsert = arr[idx];
            arr = rightShiftSubArrayByAPosition(arr, indexToInsertAt, idx);
            arr[indexToInsertAt] = valToInsert;
        }
        return arr;
    }

    private Integer[] rightShiftSubArrayByAPosition(Integer[] arr, int startIdxToShift, int endIdxToShift) {
        for (int i = endIdxToShift; i > startIdxToShift; i--) {
            arr[i] = arr[i - 1];
        }
        return arr;
    }

    private int indexToInsertAt(Integer[] arr, int arrayStartIdx, int arrayEndIdx, Integer elementToInsert) {
        for (int i = arrayStartIdx; i < arrayEndIdx + 1; i++) {
            if (arr[i] > elementToInsert) {
                return i;
            }
        }
        return arrayEndIdx + 1;
    }

    public static void main(String[] args) {
        Integer[] unsorted = { 64, 25, 12, 22, 11 };
        System.out.println(asList(unsorted));
        System.out.println(asList(new InsertionSort().sort(unsorted)));
    }
}
