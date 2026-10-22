class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        
        // Check Type 0: rotation of [0, 1, ..., n-1]
        Integer s0 = null;
        boolean valid0 = true;
        for (int i = 0; i < n; i++) {
            int shift = (i - nums[i] + n) % n;
            if (s0 == null) {
                s0 = shift;
            } else if (s0 != shift) {
                valid0 = false;
                break;
            }
        }
        
        // Check Type 1: rotation of [n-1, n-2, ..., 0]
        Integer s1 = null;
        boolean valid1 = true;
        for (int i = 0; i < n; i++) {
            int shift = (i + nums[i] - (n - 1) + n) % n;
            if (s1 == null) {
                s1 = shift;
            } else if (s1 != shift) {
                valid1 = false;
                break;
            }
        }

        if (!valid0 && !valid1) {
            return -1;
        }

        // BFS on 2n states: (type, shift)
        int[][] dist = new int[2][n];
        for (int i = 0; i < 2; i++) {
            java.util.Arrays.fill(dist[i], -1);
        }

        java.util.Queue<int[]> queue = new java.util.LinkedList<>();
        queue.offer(new int[]{0, 0});
        dist[0][0] = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int t = curr[0];
            int s = curr[1];
            int d = dist[t][s];

            // Op 1: Rotate Left
            int nt = t;
            int ns = (s + 1) % n;
            if (dist[nt][ns] == -1) {
                dist[nt][ns] = d + 1;
                queue.offer(new int[]{nt, ns});
            }

            // Op 2: Reverse
            nt = 1 - t;
            ns = (n - s) % n;
            if (dist[nt][ns] == -1) {
                dist[nt][ns] = d + 1;
                queue.offer(new int[]{nt, ns});
            }
        }

        int ans = Integer.MAX_VALUE;
        if (valid0 && dist[0][s0] != -1) {
            ans = Math.min(ans, dist[0][s0]);
        }
        if (valid1 && dist[1][s1] != -1) {
            ans = Math.min(ans, dist[1][s1]);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}