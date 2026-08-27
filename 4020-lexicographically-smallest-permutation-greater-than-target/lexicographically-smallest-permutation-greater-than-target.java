class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        char[] res = new char[n];
        return backtrack(0, 0, count, res, s, target);
    }

    private String backtrack(int idx, int matchLen, int[] count, char[] res, String s, String target) {
        if (idx == target.length()) {
            // Check if this permutation is strictly greater than target
            for (int i = 0; i < target.length(); i++) {
                if (res[i] > target.charAt(i)) return new String(res);
                if (res[i] < target.charAt(i)) return "";
            }
            return ""; // Equal is not allowed (must be strictly greater)
        }

        int targetChar = target.charAt(idx) - 'a';

        // Try exact match first if available
        if (count[targetChar] > 0) {
            count[targetChar]--;
            res[idx] = target.charAt(idx);
            String ans = backtrack(idx + 1, matchLen + 1, count, res, s, target);
            if (!ans.isEmpty()) return ans;
            count[targetChar]++;
        }

        // Try the smallest character strictly greater than target[idx]
        for (int c = targetChar + 1; c < 26; c++) {
            if (count[c] > 0) {
                count[c]--;
                res[idx] = (char) ('a' + c);
                // Fill the rest with the smallest remaining characters
                int ptr = idx + 1;
                int[] tempCount = count.clone();
                for (int i = 0; i < 26; i++) {
                    while (tempCount[i] > 0) {
                        res[ptr++] = (char) ('a' + i);
                        tempCount[i]--;
                    }
                }
                return new String(res);
            }
        }

        return "";
    }
}