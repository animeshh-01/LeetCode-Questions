import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounts = new int[30];
        
        // Step 1: Count set bits at each position
        for (int x : nums) {
            for (int i = 0; i < 30; i++) {
                if ((x & (1 << i)) != 0) {
                    bitCounts[i]++;
                }
            }
        }
        
        long mod = 1_000_000_007;
        long totalSum = 0;
        
        // Step 2: Construct the k largest possible numbers
        for (int step = 0; step < k; step++) {
            long currentVal = 0;
            for (int i = 0; i < 30; i++) {
                if (bitCounts[i] > 0) {
                    currentVal |= (1L << i);
                    bitCounts[i]--;
                }
            }
            totalSum = (totalSum + (currentVal * currentVal) % mod) % mod;
        }
        
        return (int) totalSum;
    }
}