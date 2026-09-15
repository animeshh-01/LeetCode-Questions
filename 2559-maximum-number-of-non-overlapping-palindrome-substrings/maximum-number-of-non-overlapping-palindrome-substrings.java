class Solution {
    private boolean[][] isPal;
    private int[] memo;
    private int n, k;

    public int maxPalindromes(String s, int k) {
        this.n = s.length();
        this.k = k;
        this.isPal = new boolean[n][n];
        this.memo = new int[n];
        java.util.Arrays.fill(memo, -1);

        // Precompute palindrome table for all substrings
        for (int i = 0; i < n; i++) {
            isPal[i][i] = true;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    isPal[i][j] = (len == 2) ? true : isPal[i + 1][j - 1];
                }
            }
        }

        return dfs(0, s);
    }

    private int dfs(int i, String s) {
        if (i >= n) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }

        // Option 1: Skip the current character
        int maxCount = dfs(i + 1, s);

        // Option 2: Try to form a palindrome starting at i with length >= k
        for (int j = i + k - 1; j < n; j++) {
            if (isPal[i][j]) {
                maxCount = Math.max(maxCount, 1 + dfs(j + 1, s));
                // Optimization: choosing the earliest ending palindrome of length >= k 
                // starting at i is optimal for this specific branch.
                break; 
            }
        }

        return memo[i] = maxCount;
    }
}