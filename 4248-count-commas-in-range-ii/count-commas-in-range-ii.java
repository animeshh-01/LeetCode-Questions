class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        
        // Digit ranges start at 4, 7, 10, 13, 16... up to 10^15
        long start = 1000;
        long commasPerNumber = 1;
        
        while (start <= n) {
            long end = Math.min(n, (start * 1000) - 1);
            long count = (end - start + 1);
            totalCommas += count * commasPerNumber;
            
            // Move to the next comma group (e.g., from thousands to millions)
            start *= 1000;
            commasPerNumber++;
        }
        
        return totalCommas;
    }
}