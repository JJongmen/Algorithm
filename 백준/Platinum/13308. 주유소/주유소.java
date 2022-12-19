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
    public static final long INF = 30000000000L;
    static int N, M;
    static int[] prices;
    static List<Node>[] graph;

    static class Node {
        int to;
        int dist;
        int min;
        long cost;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        public Node(int to, int min, long cost) {
            this.to = to;
            this.min = min;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        prices = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

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

        long[][] d = new long[N + 1][2501];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(d[i], INF);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.cost));
        pq.offer(new Node(1, prices[1], 0));
        d[1][prices[1]] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (d[cur.to][cur.min] != cur.cost) continue;
            for (Node nxt : graph[cur.to]) {
                int nxtMin = Math.min(cur.min, prices[cur.to]);
                long nxtCost = d[cur.to][cur.min] + (long) nxt.dist * nxtMin;
                if (d[nxt.to][nxtMin] <= nxtCost) continue;
                d[nxt.to][nxtMin] = nxtCost;
                pq.offer(new Node(nxt.to, nxtMin, nxtCost));
            }
        }

        long result = INF;
        for (int i = 1; i <= 2500; i++) {
            result = Math.min(result, d[N][i]);
        }
        System.out.println(result);
    }
}