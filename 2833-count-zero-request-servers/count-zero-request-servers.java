import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        
        // Store queries with their original indices so we can place answers back correctly
        int[][] indexedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            indexedQueries[i][0] = queries[i];
            indexedQueries[i][1] = i;
        }
        
        // Sort queries by time
        Arrays.sort(indexedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Sort logs by time
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int[] result = new int[m];
        int[] serverCount = new int[n + 1];
        int activeServers = 0;
        
        int left = 0;
        int right = 0;
        int numLogs = logs.length;
        
        for (int i = 0; i < m; i++) {
            int qTime = indexedQueries[i][0];
            int originalIdx = indexedQueries[i][1];
            
            int windowStart = qTime - x;
            int windowEnd = qTime;
            
            // Expand right pointer to include logs up to windowEnd
            while (right < numLogs && logs[right][1] <= windowEnd) {
                int serverId = logs[right][0];
                if (serverCount[serverId] == 0) {
                    activeServers++;
                }
                serverCount[serverId]++;
                right++;
            }
            
            // Shrink left pointer to remove logs older than windowStart
            while (left < numLogs && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                serverCount[serverId]--;
                if (serverCount[serverId] == 0) {
                    activeServers--;
                }
                left++;
            }
            
            // Zero-request servers = total servers - active servers in the window
            result[originalIdx] = n - activeServers;
        }
        
        return result;
    }
}