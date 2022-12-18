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
    public static final int INF = 1000000005;
    static int N, M, K;
    static List<Node>[] graph;

    static class Node {
        int to;
        int cost;
        int dist;

        public Node(int to, int cost, int dist) {
            this.to = to;
            this.cost = cost;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            graph = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                graph[u].add(new Node(v, c, d));
            }

            int[][] d = new int[N + 1][M + 1];
            for (int i = 1; i <= N; i++) {
                Arrays.fill(d[i], INF);
            }
            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
            pq.offer(new Node(1, 0, 0));
            Arrays.fill(d[1], 0);
            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                if (d[cur.to][cur.cost] != cur.dist) continue;
                for (Node nxt : graph[cur.to]) {
                    int nxtCost = cur.cost + nxt.cost;
                    if (nxtCost > M) continue;
                    int nxtDist = d[cur.to][cur.cost] + nxt.dist;
                    if (d[nxt.to][nxtCost] <= nxtDist) continue;
                    d[nxt.to][nxtCost] = nxtDist;
                    pq.offer(new Node(nxt.to, nxtCost, nxtDist));
                }
            }
            int min = INF;
            for (int i = 0; i <= M; i++) {
                if (d[N][i] == INF) continue;
                min = Math.min(min, d[N][i]);
            }
            bw.write(min == INF ? "Poor KCM\n" : min + "\n");

        }
        bw.flush();
        bw.close();
        br.close();
    }
}