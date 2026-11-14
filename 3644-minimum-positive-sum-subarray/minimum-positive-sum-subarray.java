import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;
        
        // Iterate over all possible subarray lengths from l to r
        for (int len = l; len <= r; len++) {
            // Sliding window or direct summation for each starting index
            for (int i = 0; i <= n - len; i++) {
                int currentSum = 0;
                for (int j = i; j < i + len; j++) {
                    currentSum += nums.get(j);
                }
                
                // We only care about sums strictly greater than 0
                if (currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                }
            }
        }
        
        // If minSum wasn't updated, no valid subarray was found
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}