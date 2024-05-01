import java.util.*;

class Solution {
    private static boolean[] visit = new boolean[100];
    private static PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
    
    public int dfs(int cur, int cnt, int[] cards) {
        visit[cur] = true;
        int nxt = cards[cur] - 1;
        if (visit[nxt]) return cnt;
        return dfs(nxt, cnt + 1, cards);
    }
    
    public int solution(int[] cards) {
        for (int i = 0; i < cards.length; i++) {
            if (visit[i]) continue;
            pq.offer(dfs(i, 1, cards));
        }
        if (pq.size() == 1) return 0;
        return pq.poll() * pq.poll();
    }
}