class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int n = s.length();
        long[] dp = new long[n + 1];
        dp[0] = 1; // Base case for empty prefix conceptually
        
        int[] last = new int[26];
        java.util.Arrays.fill(last, -1);
        
        for (int i = 0; i < n; i++) {
            int x = s.charAt(i) - 'a';
            long total = (dp[i] * 2) % MOD;
            
            if (last[x] != -1) {
                long duplicate = (last[x] > 0) ? dp[last[x] - 1] : 1;
                total = (total - duplicate + MOD) % MOD;
            } else {
                total = (total + 1) % MOD; // Account for the single character itself if it's the first time
            }
            
            dp[i + 1] = total;
            last[x] = i;
        }
        
        // Since the problem asks for non-empty subsequences and dp[n] includes the empty string implicitly if calculated from 0, 
        // let's adjust or track total distinct ending sums properly.
        // Alternatively, maintain a running sum of ends with each character.
        
        long[] lastEnd = new long[26];
        long totalSum = 0;
        
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            long newSub = (totalSum + 1) % MOD;
            totalSum = (totalSum - lastEnd[idx] + newSub + MOD) % MOD;
            lastEnd[idx] = newSub;
        }
        
        return (int) totalSum;
    }
}