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
    public static final long INF = 100_000_000_000_005L;
    static int N, M, A, B;
    static long C;

    static List<Node>[] graph;

    static class Node {
        int to;
        long cost;
        long shame;

        public Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        public Node(int to, long cost, long shame) {
            this.to = to;
            this.cost = cost;
            this.shame = shame;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Long.parseLong(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        long[] d = new long[N + 1];
        Arrays.fill(d, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.shame));
        pq.offer(new Node(A, 0, 0));
        d[A] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (d[cur.to] != cur.shame) continue;
            for (Node nxt : graph[cur.to]) {
                long nxtShame = Math.max(d[cur.to], nxt.cost);
                if (d[nxt.to] <= nxtShame) continue;
                long nxtCost = cur.cost + nxt.cost;
                if (nxtCost > C) continue;
                d[nxt.to] = nxtShame;
                pq.offer(new Node(nxt.to, nxtCost, nxtShame));
            }
        }
        System.out.println(d[B] == INF ? -1 : d[B]);
    }
}