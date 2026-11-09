import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // TreeSet to store available server indices
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
        
        // Min-heap to store busy servers: stores int[] {freeTime, serverId}
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        int[] requestCount = new int[k];
        int maxRequests = 0;
        
        for (int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            
            // Free up servers whose tasks have finished by currTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currTime) {
                int[] freed = busyServers.poll();
                availableServers.add(freed[1]);
            }
            
            // If all servers are busy, drop the request
            if (availableServers.isEmpty()) {
                continue;
            }
            
            // Find the next available server starting from (i % k)
            Integer serverId = availableServers.ceiling(i % k);
            if (serverId == null) {
                serverId = availableServers.first(); // Wrap around to 0
            }
            
            // Assign request to this server
            availableServers.remove(serverId);
            int freeTime = currTime + load[i];
            busyServers.offer(new int[] { freeTime, serverId });
            
            requestCount[serverId]++;
            maxRequests = Math.max(maxRequests, requestCount[serverId]);
        }
        
        // Collect all servers that handled the maximum number of requests
        List<Integer> busiest = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                busiest.add(i);
            }
        }
        
        return busiest;
    }
}