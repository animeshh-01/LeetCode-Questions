import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list: u -> list of [v, w]
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[] { edge[1], edge[2] });
        }

        // Min-heap storing {current_weight, current_node, consecutive_count}
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        // Distances table to avoid redundant visits: dist[node][count]
        // Since count ranges from 1 to k, size k + 1 is sufficient.
        long[][] dist = new long[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        // Base case: start at node 0
        // Initial label appears 1 time with 0 total weight
        pq.offer(new long[] { 0, 0, 1 });
        dist[0][1] = 0;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            if (d > dist[u][count]) {
                continue;
            }

            // If we reached the target node, we can return the weight
            // (since Dijkstra guarantees the first time we pop target, it's optimal)
            if (u == n - 1) {
                return (int) d;
            }

            for (int[] neighbor : graph[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];

                int nextCount;
                if (labels.charAt(u) == labels.charAt(v)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                // Check if the consecutive count is within the allowed limit k
                if (nextCount <= k) {
                    if (d + weight < dist[v][nextCount]) {
                        dist[v][nextCount] = d + weight;
                        pq.offer(new long[] { d + weight, v, nextCount });
                    }
                }
            }
        }

        // Find minimum distance to reach node n - 1 across all valid counts
        long minWeight = Long.MAX_VALUE;
        for (int count = 1; count <= k; count++) {
            minWeight = Math.min(minWeight, dist[n - 1][count]);
        }

        return minWeight == Long.MAX_VALUE ? -1 : (int) minWeight;
    }
}