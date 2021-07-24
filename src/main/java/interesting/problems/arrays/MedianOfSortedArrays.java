package interesting.problems.arrays;

import java.util.Arrays;

public class MedianOfSortedArrays {
    public static void main(String[] args) {
        new MedianOfSortedArrays().findMedianSortedArrays(new int[]{1, 3}, new int[]{2});
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merged = merge(nums1, nums2);
        System.out.println(Arrays.asList(merged));
        if (merged.length % 2 == 0) {
            return (merged[(merged.length - 1) / 2] + merged[merged.length / 2]) / 2d;
        }
        return merged[merged.length / 2];
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int[] merged = new int[nums1.length + nums2.length];
        int idx1 = 0, idx2 = 0;
        while (idx1 < nums1.length && idx2 < nums2.length) {
            if (nums1[idx1] <= nums2[idx2]) {
                merged[idx1 + idx2] = nums1[idx1++];
            } else {
                merged[idx1 + idx2] = nums2[idx2++];
            }
        }
        if (idx1 < nums1.length) {
            System.arraycopy(nums1, idx1, merged, idx1 + idx2, nums1.length - idx1);
        }
        if (idx2 < nums2.length) {
            System.arraycopy(nums2, idx2, merged, idx1 + idx2, nums2.length - idx2);
        }
        return merged;
    }
}
