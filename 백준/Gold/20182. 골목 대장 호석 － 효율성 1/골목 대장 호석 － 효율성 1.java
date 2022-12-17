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
    public static final int INF = 2000005;
    static int N, M, A, B, C;

    static List<Node>[] graph;

    static class Node {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

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

        int[] d = new int[N + 1];
        Arrays.fill(d, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        pq.offer(new Node(A, 0));
        d[A] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            for (Node nxt : graph[cur.to]) {
                int nxtCost = cur.cost + nxt.cost;
                if (nxtCost > C) continue;
                int nxtShame = Math.max(d[cur.to], nxt.cost);
                if (d[nxt.to] <= nxtShame) continue;
                d[nxt.to] = nxtShame;
                pq.offer(new Node(nxt.to, nxtCost));
            }
        }
        System.out.println(d[B] == INF ? -1 : d[B]);
    }
}