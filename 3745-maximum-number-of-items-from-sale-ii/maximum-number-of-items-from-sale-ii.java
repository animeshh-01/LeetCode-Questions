import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            if (item[0] > maxFactor) {
                maxFactor = item[0];
            }
            if (item[1] < minPrice) {
                minPrice = item[1];
            }
        }

        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        long[] gainByFactor = new long[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            long multiplesCount = freq[f] - 1;
            for (int m = 2 * f; m <= maxFactor; m += f) {
                multiplesCount += freq[m];
            }
            gainByFactor[f] = Math.max(0L, multiplesCount);
        }

        int[][] sorted = new int[n][2];
        for (int i = 0; i < n; i++) {
            sorted[i][0] = items[i][0];
            sorted[i][1] = items[i][1];
        }
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[1], b[1]));

        long totalItems = 0L;
        long remainingBudget = budget;
        long twiceMinPrice = 2L * minPrice;

        // A boosted unit (1 bought + 1 free = 2 items) beats buying minPrice items
        // (1 item per minPrice, i.e., 2 items per 2 * minPrice) whenever price < 2 * minPrice
        for (int[] item : sorted) {
            int factor = item[0];
            long price = item[1];
            long gain = gainByFactor[factor];

            if (gain > 0 && price < twiceMinPrice && remainingBudget >= price) {
                long units = Math.min(gain, remainingBudget / price);
                totalItems += units * 2L;
                remainingBudget -= units * price;
                if (units < gain) {
                    break;
                }
            }
        }

        if (remainingBudget >= minPrice) {
            totalItems += remainingBudget / minPrice;
        }

        return (int) totalItems;
    }
}