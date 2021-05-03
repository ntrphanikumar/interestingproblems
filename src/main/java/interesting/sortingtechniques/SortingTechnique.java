package interesting.sortingtechniques;

public interface SortingTechnique {
    Integer[] sort(Integer[] arr);
    
    default Integer[] swapIdxVals(Integer[] arr, int idx1, int idx2) {
        if (idx1 != idx2) {
            arr[idx1] += arr[idx2];
            arr[idx2] = arr[idx1] - arr[idx2];
            arr[idx1] = arr[idx1] - arr[idx2];
        }
        return arr;
    }
}
