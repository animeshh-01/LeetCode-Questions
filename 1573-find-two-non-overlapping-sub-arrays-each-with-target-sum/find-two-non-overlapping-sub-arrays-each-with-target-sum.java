class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        java.util.Arrays.fill(minLen, Integer.MAX_VALUE);
        
        int sum = 0, left = 0;
        int result = Integer.MAX_VALUE;
        int bestSoFar = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            
            if (sum == target) {
                int currLen = right - left + 1;
                
                // If there is a valid non-overlapping sub-array before `left`
                if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE) {
                    result = Math.min(result, currLen + minLen[left - 1]);
                }
                
                // Update minLen ending at `right`
                bestSoFar = Math.min(bestSoFar, currLen);
            }
            
            minLen[right] = bestSoFar;
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}