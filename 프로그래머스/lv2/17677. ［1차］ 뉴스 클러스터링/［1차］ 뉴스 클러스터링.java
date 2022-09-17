import java.util.*;
import java.util.regex.Pattern;

class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> mapA = new HashMap<>();
        Map<String, Integer> mapB = new HashMap<>();

        String subStr = null;
        for (int i = 0; i < str1.length() - 1; i++) {
            subStr = str1.substring(i, i + 2);
            if (Pattern.matches("[a-zA-Z]{2}", subStr)) {
                subStr = subStr.toUpperCase();
                mapA.put(subStr, mapA.getOrDefault(subStr, 0) + 1);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            subStr = str2.substring(i, i + 2);
            if (Pattern.matches("[a-zA-Z]{2}", subStr)) {
                subStr = subStr.toUpperCase();
                mapB.put(subStr, mapB.getOrDefault(subStr, 0) + 1);
            }
        }

        Set<String> allElements = new HashSet<>();
        allElements.addAll(mapA.keySet());
        allElements.addAll(mapB.keySet());
        if (allElements.isEmpty()) {
            return 65536;
        }
        int union = 0;
        double intersection = 0;
        for (String element : allElements) {
            Integer aElemCnt = mapA.getOrDefault(element, 0);
            Integer bElemCnt = mapB.getOrDefault(element, 0);
            union += Math.max(aElemCnt, bElemCnt);
            intersection += Math.min(aElemCnt, bElemCnt);
        }
        return (int) (intersection / union * 65536);
    }
}