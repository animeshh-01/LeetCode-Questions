class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long common = Math.min(need1, need2);
        long costCommon = Math.min((long) costBoth, (long) cost1 + cost2);
        
        long totalCost = common * costCommon;
        
        long rem1 = need1 - common;
        long rem2 = need2 - common;
        
        if (rem1 > 0) {
            totalCost += rem1 * Math.min((long) costBoth, (long) cost1);
        }
        if (rem2 > 0) {
            totalCost += rem2 * Math.min((long) costBoth, (long) cost2);
        }
        
        return totalCost;
    }
}