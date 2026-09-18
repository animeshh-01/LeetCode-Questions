import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        // Step 1: Record first and last occurrences of each character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Step 2: Find valid substrings by expanding boundaries
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;
            
            int l = first[i];
            int r = last[i];
            boolean isValid = true;

            // Expand range to include all characters fully contained
            for (int j = l; j <= r; j++) {
                int c = s.charAt(j) - 'a';
                // If a character's first occurrence is outside our left bound, 
                // this interval is invalid / overlaps partially.
                if (first[c] < l) {
                    isValid = false;
                    break;
                }
                r = Math.max(r, last[c]);
            }

            if (isValid) {
                intervals.add(new int[]{l, r});
            }
        }

        // Step 3: Greedy interval scheduling (sort by ending index)
        intervals.sort(Comparator.comparingInt(a -> a[1]));

        List<String> result = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            // If the current interval does not overlap with the previous one, pick it
            if (start > prevEnd) {
                result.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }

        return result;
    }
}