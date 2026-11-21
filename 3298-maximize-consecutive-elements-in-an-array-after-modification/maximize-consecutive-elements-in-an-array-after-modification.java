import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int x : nums) {
            // 1. Try using x + 1 first so it doesn't reuse the updated dp[x] from this same element
            int up = x + 1;
            int upLen = dp.getOrDefault(up - 1, 0) + 1;
            dp.put(up, Math.max(dp.getOrDefault(up, 0), upLen));
            ans = Math.max(ans, dp.get(up));

            // 2. Try using x as-is
            int curLen = dp.getOrDefault(x - 1, 0) + 1;
            dp.put(x, Math.max(dp.getOrDefault(x, 0), curLen));
            ans = Math.max(ans, dp.get(x));
        }

        return ans;
    }
}