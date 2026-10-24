class Solution {
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int arriveAliceDays = calculateDayOfYear(arriveAlice);
        int leaveAliceDays = calculateDayOfYear(leaveAlice);
        int arriveBobDays = calculateDayOfYear(arriveBob);
        int leaveBobDays = calculateDayOfYear(leaveBob);

        int overlapStart = Math.max(arriveAliceDays, arriveBobDays);
        int overlapEnd = Math.min(leaveAliceDays, leaveBobDays);

        if (overlapEnd >= overlapStart) {
            return overlapEnd - overlapStart + 1;
        }
        return 0;
    }

    private int calculateDayOfYear(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int totalDays = day;
        for (int i = 0; i < month - 1; i++) {
            totalDays += DAYS_IN_MONTH[i];
        }
        return totalDays;
    }
}