class Solution {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int digitSum = 0;
            int temp = nums[i];
            
            // Calculate sum of digits of nums[i]
            while (temp > 0) {
                digitSum += temp % 10;
                temp /= 10;
            }
            
            // Handle edge case where nums[i] is 0 (digit sum is 0)
            if (nums[i] == 0 && i == 0) {
                return 0;
            }
            
            if (digitSum == i) {
                return i;
            }
        }
        return -1;
    }
}