class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] prefix = new int[n];
        prefix[0] = stones[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }
        
        // Base case: when only 1 stone is left, the score difference is 0.
        // We evaluate backwards from index n-1 down to 1.
        int res = prefix[n - 1];
        for (int i = n - 2; i >= 1; i--) {
            res = Math.max(res, prefix[i] - res);
        }
        
        return res;
    }
}