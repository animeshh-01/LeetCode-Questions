class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k * k <= i; k++) {
                // If moving to a state where the opponent loses, then this is a winning state
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
}