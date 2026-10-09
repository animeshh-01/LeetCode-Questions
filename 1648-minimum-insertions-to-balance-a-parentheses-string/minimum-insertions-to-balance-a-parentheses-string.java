class Solution {
    public int minInsertions(String s) {
        int res = 0, open = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                // If it's a closing parenthesis, check if there's another ')' right after
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i++; // Skip the next ')' as it forms a pair
                } else {
                    // Missing one ')' to form a pair
                    res++;
                }
                
                // Match with an open parenthesis if available
                if (open > 0) {
                    open--;
                } else {
                    // Need to insert an open parenthesis to match this pair
                    res++;
                }
            }
        }
        
        // Each remaining open parenthesis needs two closing parentheses
        res += open * 2;
        
        return res;
    }
}