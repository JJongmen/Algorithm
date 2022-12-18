import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 100000000;
    static int N, M, K;
    static List<Node>[] graph;
    static int[][] d;

    static class Node {
        int to;
        int cost;
        int cnt;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public Node(int to, int cost, int cnt) {
            this.to = to;
            this.cost = cost;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, w));
            graph[b].add(new Node(a, w));
        }


        // TODO
        d = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(d[i], INF);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        pq.offer(new Node(S, 0, 0));
        Arrays.fill(d[S], 0);
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (d[cur.to][cur.cnt] != cur.cost) continue;
            for (Node nxt : graph[cur.to]) {
                int nxtCost = d[cur.to][cur.cnt] + nxt.cost;
                if (cur.cnt + 1 > N || d[nxt.to][cur.cnt + 1] <= nxtCost) continue;
                d[nxt.to][cur.cnt + 1] = nxtCost;
                if (cur.cnt + 2 <= N) {
                    d[nxt.to][cur.cnt + 2] = Math.min(d[nxt.to][cur.cnt + 2], d[nxt.to][cur.cnt + 1]);
                }
                pq.offer(new Node(nxt.to, nxtCost, cur.cnt + 1));
            }
        }

        int increaseCost = 0;
        bw.write(getMinCost(D, increaseCost) + "\n");
        while (K-- > 0) {
            increaseCost += Integer.parseInt(br.readLine());
            bw.write(getMinCost(D, increaseCost) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getMinCost(int vertex, int increaseCost) {
        int min = INF;
        for (int i = 1; i <= N; i++) {
            min = Math.min(min, d[vertex][i] + i * increaseCost);
        }
        return min;
    }
}