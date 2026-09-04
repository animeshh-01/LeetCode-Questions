class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // suffMin[i] will store the minimum value in nums[i...n-1]
        int[] suffMin = new int[n];
        suffMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffMin[i] = Math.min(suffMin[i + 1], nums[i]);
        }
        
        int currMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            currMax = Math.max(currMax, nums[i]);
            if (currMax - suffMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}