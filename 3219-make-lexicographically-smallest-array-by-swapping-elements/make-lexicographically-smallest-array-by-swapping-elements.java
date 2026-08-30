import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] indexedNums = new int[n][2];
        for (int i = 0; i < n; i++) {
            indexedNums[i][0] = nums[i];
            indexedNums[i][1] = i;
        }

        // Sort by value
        Arrays.sort(indexedNums, (a, b) -> Integer.compare(a[0], b[0]));

        int[] res = new int[n];
        int i = 0;
        while (i < n) {
            int j = i;
            // Find all elements that belong to the same connected component
            while (j < n && indexedNums[j][0] - indexedNums[j - 1 < i ? i : j - 1][0] <= limit) {
                // To keep the condition clean, check difference with the start or previous
                j++;
            }
            // A safer condition for group boundary check:
            // Actually, comparing with the immediate previous element is enough:
            // indexedNums[k][0] - indexedNums[k-1][0] <= limit
            j = i;
            while (j < n) {
                if (j > i && indexedNums[j][0] - indexedNums[j - 1][0] > limit) {
                    break;
                }
                j++;
            }

            // Collect indices and values for the current group
            List<Integer> values = new ArrayList<>();
            List<Integer> indices = new ArrayList<>();
            for (int k = i; k < j; k++) {
                values.add(indexedNums[k][0]);
                indices.add(indexedNums[k][1]);
            }

            // Sort indices to place smaller values into smaller original positions
            Collections.sort(indices);
            for (int k = 0; k < indices.size(); k++) {
                res[indices.get(k)] = values.get(k);
            }

            i = j;
        }

        return res;
    }
}