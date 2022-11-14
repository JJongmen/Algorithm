import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] dist = new int[20003];
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static int maxDist;

    private static void init() {
        for (int i = 0; i <= N; i++) {
            graph.add(new LinkedList<>());
        }
        Arrays.fill(dist, 0, N + 1, -1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
            graph.get(B).add(A);
        }

        // 1번 헛간에서 BFS 탐색 시작
        Queue<Integer> que = new LinkedList<>();
        que.offer(1);
        dist[1] = 0;
        while (!que.isEmpty()) {
            Integer cur = que.poll();
            for (Integer nxt : graph.get(cur)) {
                if (dist[nxt] != -1) continue;
                que.offer(nxt);
                dist[nxt] = dist[cur] + 1;
                if (maxDist < dist[nxt]) {
                    pq.clear();
                    maxDist = dist[nxt];
                }
                pq.offer(nxt);
            }
        }
        System.out.printf("%d %d %d", pq.peek(), maxDist, pq.size());
    }
}