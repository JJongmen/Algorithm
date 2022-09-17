import java.util.*;

class Solution {
   public static int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return 5 * cities.length;
        }
        int answer = 0;
        Queue<String> cache = new LinkedList<>();

        for (String city : cities) {
            city = city.toLowerCase();
            if (cache.contains(city)) {
                answer += 1;
                cache.remove(city);
                cache.offer(city);
            } else {
                if (cache.size() >= cacheSize) {
                    cache.poll();
                }
                answer += 5;
                cache.offer(city);
            }
        }
        return answer;
    }
}