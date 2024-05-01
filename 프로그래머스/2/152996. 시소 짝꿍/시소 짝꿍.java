import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Map<Integer, Integer> counter = new HashMap<>();
        for (int weight : weights) {
            counter.put(weight, counter.getOrDefault(weight, 0) + 1);
        }
        
        for (int weight : counter.keySet()) {
            int cnt = counter.get(weight);
            answer += (long) cnt * (cnt - 1) / 2;
            answer += (long) cnt * counter.getOrDefault(weight * 2, 0);
            if (weight % 2 == 0) {
                answer += (long) cnt * counter.getOrDefault(weight * 3 / 2, 0);
            }
            if (weight % 3 == 0) {
                answer += (long) cnt * counter.getOrDefault(weight * 4 / 3, 0);
            }
        }
        return answer;
    }
}