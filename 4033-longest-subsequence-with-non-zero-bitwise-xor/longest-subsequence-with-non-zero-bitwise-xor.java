class Solution {
    public int longestSubsequence(int[] nums) {
        int xorAll = 0;
        boolean hasNonZero = false;
        
        for (int num : nums) {
            xorAll ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }
        
        // If the entire array's XOR is non-zero, take the whole array
        if (xorAll != 0) {
            return nums.length;
        }
        
        // If total XOR is 0, but all elements are 0, no non-zero subsequence is possible
        if (!hasNonZero) {
            return 0;
        }
        
        // If total XOR is 0 and there are non-zero elements, we can omit one element
        return nums.length - 1;
    }
}