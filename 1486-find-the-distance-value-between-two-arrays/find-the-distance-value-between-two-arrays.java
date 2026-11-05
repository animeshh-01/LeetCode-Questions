import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int distanceValue = 0;

        for (int x : arr1) {
            if (isValid(arr2, x, d)) {
                distanceValue++;
            }
        }

        return distanceValue;
    }

    private boolean isValid(int[] arr2, int target, int d) {
        int left = 0;
        int right = arr2.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if the current element falls within the range [target - d, target + d]
            if (Math.abs(arr2[mid] - target) <= d) {
                return false; // Found an element too close, so this arr1[i] is invalid
            }

            if (arr2[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return true; // No element from arr2 is within distance d
    }
}