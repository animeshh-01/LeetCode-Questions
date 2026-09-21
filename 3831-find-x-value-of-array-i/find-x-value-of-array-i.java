class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        
        // dp[r] stores the number of valid subarrays ending at the current index with product % k == r
        long[] dp = new long[k];
        
        for (int num : nums) {
            long[] nextDp = new long[k];
            int val = num % k;
            
            // A new subarray starting at the current element
            nextDp[val]++;
            
            // Extend all previous subarrays ending at the previous index
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (int) ((1L * r * val) % k);
                    nextDp[newRem] += dp[r];
                }
            }
            
            dp = nextDp;
            
            // Accumulate counts into the final result array
            for (int r = 0; r < k; r++) {
                result[r] += dp[r];
            }
        }
        
        return result;
    }
}