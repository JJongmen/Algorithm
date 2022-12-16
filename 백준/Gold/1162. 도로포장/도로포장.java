import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static final long INF = 100_000_000_005L;
    static int N, M, K;
    static List<Node>[] graph;

    static class Node {
        int to;
        long dist;
        int cnt;

        public Node(int to, long dist) {
            this.to = to;
            this.dist = dist;
        }

        public Node(int to, long dist, int cnt) {
            this.to = to;
            this.dist = dist;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        long[][] d = new long[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(d[i], INF);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.dist));
        pq.offer(new Node(1, 0, 0));
        for (int i = 0; i <= K; i++) {
            d[1][i] = 0;
        }
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (d[cur.to][cur.cnt] != cur.dist) continue;
            for (Node nxt : graph[cur.to]) {
                long nxtDist = d[cur.to][cur.cnt] + nxt.dist;
                if (d[nxt.to][cur.cnt] > nxtDist) {
                    d[nxt.to][cur.cnt] = nxtDist;
                    pq.offer(new Node(nxt.to, nxtDist, cur.cnt));
                }
                if (cur.cnt != 0) {
                    d[nxt.to][cur.cnt] = Math.min(d[nxt.to][cur.cnt], d[nxt.to][cur.cnt - 1]);
                }
                if (cur.cnt < K && d[nxt.to][cur.cnt + 1] > cur.dist) {
                    d[nxt.to][cur.cnt + 1] = cur.dist;
                    pq.offer(new Node(nxt.to, cur.dist, cur.cnt + 1));
                }
            }
        }

        System.out.println(d[N][K]);
    }
}