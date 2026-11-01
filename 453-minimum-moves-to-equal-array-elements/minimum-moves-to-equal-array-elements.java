class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        long sum = 0; // Using long to prevent potential integer overflow during summation
        
        for (int num : nums) {
            sum += num;
            if (num < min) {
                min = num;
            }
        }
        
        return (int) (sum - (long) min * nums.length);
    }
}