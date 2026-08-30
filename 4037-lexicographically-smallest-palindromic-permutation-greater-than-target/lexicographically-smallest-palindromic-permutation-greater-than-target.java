import java.util.*;

class Solution {
    private String midCharStr;
    private char[] targetChars;
    private int halfLen;
    private Map<Character, Integer> available;
    private String finalResult;

    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        List<Character> oddChars = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddChars.add((char) (i + 'a'));
            }
        }

        int maxOdd = (n % 2 != 0) ? 1 : 0;
        if (oddChars.size() > maxOdd) {
            return "";
        }

        char midChar = '\0';
        if (n % 2 != 0) {
            midChar = oddChars.get(0);
            count[midChar - 'a']--;
        }

        midCharStr = (n % 2 != 0) ? String.valueOf(midChar) : "";
        halfLen = n / 2;
        targetChars = target.toCharArray();
        
        available = new TreeMap<>();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                available.put((char) (i + 'a'), count[i] / 2);
            }
        }

        finalResult = "";
        if (canForm(0, new StringBuilder(), false)) {
            return finalResult;
        }
        return "";
    }

    private boolean canForm(int idx, StringBuilder currentHalf, boolean isGreater) {
        if (idx == halfLen) {
            StringBuilder fullPal = new StringBuilder(currentHalf);
            fullPal.append(midCharStr);
            fullPal.append(new StringBuilder(currentHalf).reverse());
            String res = fullPal.toString();
            if (res.compareTo(new String(targetChars)) > 0) {
                finalResult = res;
                return true;
            }
            return false;
        }

        for (Map.Entry<Character, Integer> entry : available.entrySet()) {
            char ch = entry.getKey();
            int freq = entry.getValue();
            if (freq > 0) {
                if (!isGreater && ch < targetChars[idx]) {
                    continue;
                }

                available.put(ch, freq - 1);
                currentHalf.append(ch);

                boolean nextGreater = isGreater || (ch > targetChars[idx]);
                if (canForm(idx + 1, currentHalf, nextGreater)) {
                    return true;
                }

                currentHalf.deleteCharAt(currentHalf.length() - 1);
                available.put(ch, freq);
            }
        }
        return false;
    }
}