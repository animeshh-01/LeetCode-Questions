class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // Position in reversed alphabet: 'a' -> 26, 'b' -> 25, ..., 'z' -> 1
            int reversedAlphabetIndex = 26 - (ch - 'a');
            // 1-indexed position in the string
            int stringIndex = i + 1;
            
            totalDegree += reversedAlphabetIndex * stringIndex;
        }
        
        return totalDegree;
    }
}