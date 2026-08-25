import java.util.HashSet;
import java.util.Set;

class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        
        int multiple = k;
        while (numSet.contains(multiple)) {
            multiple += k;
        }
        
        return multiple;
    }
}