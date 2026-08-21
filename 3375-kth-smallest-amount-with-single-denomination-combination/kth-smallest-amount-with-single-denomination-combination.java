class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1, high = (long) coins[0] * k;
        for (int c : coins) {
            low = Math.min(low, c);
        }
        
        long ans = high;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (count(mid, coins) >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // Counts how many valid amounts <= x can be formed using the coins
    private long count(long x, int[] coins) {
        long total = 0;
        int n = coins.length;
        int totalSubsets = 1 << n;

        for (int mask = 1; mask < totalSubsets; mask++) {
            long lcmVal = 1;
            int setBits = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    setBits++;
                    lcmVal = lcm(lcmVal, coins[i]);
                    // If LCM exceeds x, it won't contribute any multiples <= x
                    if (lcmVal > x) {
                        break;
                    }
                }
            }

            if (lcmVal <= x) {
                if (setBits % 2 == 1) {
                    total += x / lcmVal;
                } else {
                    total -= x / lcmVal;
                }
            }
        }
        return total;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        // Prevent potential overflow during multiplication
        return (a / gcd(a, b)) * b;
    }
}