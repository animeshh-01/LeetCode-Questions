import java.util.*;

class Solution {
    class State {
        long weight;
        List<Integer> indices;

        public State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = new ArrayList<>(indices);
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        long[][] arr = new long[n][4];
        for (int i = 0; i < n; i++) {
            arr[i][0] = intervalsList.get(i).get(0);
            arr[i][1] = intervalsList.get(i).get(1);
            arr[i][2] = intervalsList.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by right endpoint, and by original index if right endpoints match
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) return Long.compare(a[1], b[1]);
            return Long.compare(a[3], b[3]);
        });

        // dp[i][j] stores the best State selecting up to j non-overlapping intervals from first i intervals
        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 4; j++) {
                dp[i][j] = new State(0, new ArrayList<>());
            }
        }

        for (int i = 0; i < n; i++) {
            long l = arr[i][0];
            long weight = arr[i][2];
            int idx = (int) arr[i][3];

            // Binary search to find the last interval whose right endpoint is < l
            int k = binarySearch(arr, i, l);

            for (int j = 1; j <= 4; j++) {
                // Option 1: Don't include current interval
                State s1 = dp[i][j];

                // Option 2: Include current interval
                long prevWeight = dp[k][j - 1].weight;
                List<Integer> prevIndices = dp[k][j - 1].indices;

                long newWeight = prevWeight + weight;
                List<Integer> newIndices = new ArrayList<>(prevIndices);
                newIndices.add(idx);
                // Keep indices sorted for correct lexicographical comparison
                Collections.sort(newIndices);

                State s2 = new State(newWeight, newIndices);

                // Compare s1 and s2
                dp[i + 1][j] = chooseBest(s1, s2);
            }
        }

        List<Integer> bestIndices = dp[n][4].indices;
        int[] result = new int[bestIndices.size()];
        for (int i = 0; i < bestIndices.size(); i++) {
            result[i] = bestIndices.get(i);
        }
        return result;
    }

    private int binarySearch(long[][] arr, int high, long targetLeft) {
        int low = 0;
        int ans = 0;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid][1] < targetLeft) {
                ans = mid + 1;
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return ans;
    }

    private State chooseBest(State s1, State s2) {
        if (s2.weight > s1.weight) return s2;
        if (s1.weight > s2.weight) return s1;

        // Weights are equal, pick lexicographically smaller list of indices
        for (int i = 0; i < Math.min(s1.indices.size(), s2.indices.size()); i++) {
            int cmp = Integer.compare(s1.indices.get(i), s2.indices.get(i));
            if (cmp < 0) return s1;
            if (cmp > 0) return s2;
        }
        return s1.indices.size() < s2.indices.size() ? s1 : s2;
    }
}