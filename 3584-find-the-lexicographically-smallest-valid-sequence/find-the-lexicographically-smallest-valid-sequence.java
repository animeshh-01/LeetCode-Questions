class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        // suf[i] will store the length of the longest suffix of word2 
        // that can be matched as a subsequence starting from index i in word1.
        int[] suf = new int[n + 1];
        
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }
            suf[i] = m - 1 - j;
        }
        
        int[] res = new int[m];
        int w2Index = 0;
        boolean usedMismatch = false;
        
        for (int i = 0; i < n; i++) {
            if (w2Index == m) break;
            
            if (word1.charAt(i) == word2.charAt(w2Index)) {
                // Exact match: always take it greedily
                res[w2Index++] = i;
            } else if (!usedMismatch && suf[i + 1] >= m - (w2Index + 1)) {
                // Mismatch allowed: use it if the remaining suffix can still be matched
                res[w2Index++] = i;
                usedMismatch = true;
            }
        }
        
        return w2Index == m ? res : new int[0];
    }
}