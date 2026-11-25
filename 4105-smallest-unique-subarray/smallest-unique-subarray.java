import java.util.*;

class Solution {
    private static final long BASE = 1315423911L;
    private static final long MOD = 1_000_000_007L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long[] pow = new long[n + 1];
        long[] pref = new long[n + 1];
        pow[0] = 1;

        for (int i = 0; i < n; i++) {
            pow[i + 1] = (pow[i] * BASE) % MOD;
            pref[i + 1] = (pref[i] * BASE + nums[i] + 1L) % MOD;
        }

        int low = 1, high = n, ans = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueOfLength(nums, pref, pow, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean hasUniqueOfLength(int[] nums, long[] pref, long[] pow, int len) {
        int n = nums.length;
        Map<Long, Integer> freq = new HashMap<>();

        for (int l = 0; l + len <= n; l++) {
            int r = l + len;
            long hash = (pref[r] - (pref[l] * pow[len]) % MOD + MOD) % MOD;
            freq.put(hash, freq.getOrDefault(hash, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}