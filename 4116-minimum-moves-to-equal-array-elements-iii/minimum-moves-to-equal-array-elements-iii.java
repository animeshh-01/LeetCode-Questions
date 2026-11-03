class Solution {
    public int minMoves(int[] nums) {
        int max = nums[0];
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        
        int totalMoves = 0;
        for (int num : nums) {
            totalMoves += (max - num);
        }
        
        return totalMoves;
    }
}