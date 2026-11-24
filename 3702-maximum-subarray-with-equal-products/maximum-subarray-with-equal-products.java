class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 0;
        final long MAX_LCM = 2520L; // lcm(1..10)

        for (int i = 0; i < n; i++) {
            long prod = 1L;
            long gcd = 0L;
            long lcm = 1L;

            for (int j = i; j < n; j++) {
                long val = nums[j];
                gcd = gcd(gcd, val);
                lcm = lcm(lcm, val);

                // Guard against overflow and prune impossible expansions
                if (prod > (MAX_LCM * gcd) / val) {
                    break;
                }
                prod *= val;

                if (prod == lcm * gcd) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private long gcd(long a, long b) {
        while (b != 0L) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}