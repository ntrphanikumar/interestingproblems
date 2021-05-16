package interesting.sortingtechniques;

import static java.util.Arrays.asList;

public class MergeSort implements SortingTechnique {

    @Override
    public Integer[] sort(Integer[] arr) {
        return sort(arr, 0, arr.length - 1);
    }

    private Integer[] sort(Integer[] arr, int startIdx, int endIdx) {
        System.out.println(asList(arr) + " " + startIdx + " " + endIdx);
        if (endIdx - startIdx > 1) {
            int middleIdx = startIdx + (endIdx - 1) / 2;
            arr = sort(arr, startIdx, middleIdx);
            arr = sort(arr, middleIdx + 1, endIdx);

            arr = merge(arr, startIdx, middleIdx, endIdx);
        }
        return arr;
    }

    private Integer[] merge(Integer[] arr, int startIdx, int middleIdx, int endIdx) {
        System.out.println(asList(arr) + " " + startIdx + " " + middleIdx + " " + endIdx);
        for (int i = startIdx; i < middleIdx;) {
            for (int j = middleIdx; j < endIdx + 1; j++) {
                if (arr[i] > arr[j]) {
                    arr = swapIdxVals(arr, i, j);
                    i++;
                    break;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Integer[] unsorted = { 64, 25, 12, 22, 11 };
        System.out.println(asList(unsorted));
        System.out.println(asList(new MergeSort().sort(unsorted)));
    }

}
