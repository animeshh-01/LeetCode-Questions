import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        // Case 1: k = 1
        if (k == 1) {
            Map<Integer, Integer> count = new HashMap<>();
            for (int x : nums) {
                count.put(x, count.getOrDefault(x, 0) + 1);
            }
            int maxVal = -1;
            for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
                if (entry.getValue() == 1) {
                    maxVal = Math.max(maxVal, entry.getKey());
                }
            }
            return maxVal;
        }
        
        // Case 2: k = n
        if (k == n) {
            int maxVal = -1;
            for (int x : nums) {
                maxVal = Math.max(maxVal, x);
            }
            return maxVal;
        }
        
        // Case 3: 1 < k < n
        // Count how many subarrays of size k contain each number
        Map<Integer, Integer> subCount = new HashMap<>();
        for (int i = 0; i <= n - k; i++) {
            // To avoid duplicate counting within the same subarray
            boolean[] seenInWindow = new boolean[51];
            for (int j = i; j < i + k; j++) {
                if (!seenInWindow[nums[j]]) {
                    subCount.put(nums[j], subCount.getOrDefault(nums[j], 0) + 1);
                    seenInWindow[nums[j]] = true;
                }
            }
        }
        
        int ans = -1;
        // Check endpoints nums[0] and nums[n - 1]
        if (subCount.getOrDefault(nums[0], 0) == 1) {
            ans = Math.max(ans, nums[0]);
        }
        if (subCount.getOrDefault(nums[n - 1], 0) == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }
        
        return ans;
    }
}