import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();

        // Collect coordinates of 1s in both images
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) {
                    list1.add(new int[]{r, c});
                }
                if (img2[r][c] == 1) {
                    list2.add(new int[]{r, c});
                }
            }
        }

        Map<String, Integer> countMap = new HashMap<>();
        int maxOverlap = 0;

        // Calculate translation vectors for every pair of 1s
        for (int[] p1 : list1) {
            for (int[] p2 : list2) {
                String vector = (p2[0] - p1[0]) + "," + (p2[1] - p1[1]);
                countMap.put(vector, countMap.getOrDefault(vector, 0) + 1);
                maxOverlap = Math.max(maxOverlap, countMap.get(vector));
            }
        }

        return maxOverlap;
    }
}