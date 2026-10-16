import java.util.HashSet;
import java.util.List;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int totalSum = 0;

        // Generate all possible subarrays
        for (int i = 0; i < n; i++) {
            HashSet<Integer> distinctElements = new HashSet<>();
            for (int j = i; j < n; j++) {
                distinctElements.add(nums.get(j));
                int count = distinctElements.size();
                totalSum += count * count;
            }
        }

        return totalSum;
    }
}