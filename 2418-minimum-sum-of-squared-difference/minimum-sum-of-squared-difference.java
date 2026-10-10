import java.util.HashMap;
import java.util.Map;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalK = (long) k1 + k2;
        
        // Map to store the frequency of each absolute difference
        Map<Integer, Long> diffCounts = new HashMap<>();
        int maxDiff = 0;
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            diffCounts.put(diff, diffCounts.getOrDefault(diff, 0L) + 1L);
            maxDiff = Math.max(maxDiff, diff);
        }
        
        // Greedily reduce differences starting from maxDiff down to 1
        for (int d = maxDiff; d > 0 && totalK > 0; d--) {
            if (!diffCounts.containsKey(d)) {
                continue;
            }
            long count = diffCounts.get(d);
            long operationsToReduce = Math.min(count, totalK);
            
            totalK -= operationsToReduce;
            diffCounts.put(d, count - operationsToReduce);
            diffCounts.put(d - 1, diffCounts.getOrDefault(d - 1, 0L) + operationsToReduce);
        }
        
        // Calculate the final minimum sum of squared differences
        long minSum = 0;
        for (Map.Entry<Integer, Long> entry : diffCounts.entrySet()) {
            long diff = entry.getKey();
            long count = entry.getValue();
            minSum += count * diff * diff;
        }
        
        return minSum;
    }
}