import java.util.ArrayList;

class Solution {
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int low = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == low) {
                low++;
            }
        }
        return low;
    }
}
