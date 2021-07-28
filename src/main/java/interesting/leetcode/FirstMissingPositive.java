package interesting.leetcode;

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        boolean[] array = new boolean[nums.length + 1];
        for (int num : nums) {
            if (num > 0 && num < array.length) {
                array[num - 1] = true;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (!array[i]) {
                return i + 1;
            }
        }
        return Integer.MAX_VALUE;
    }

}
