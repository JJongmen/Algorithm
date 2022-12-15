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
    public static final long INF = 70_000_000_005L;
    static int N, M;
    static List<Node>[] graph;

    static class Node {
        int to;
        long dist;

        public Node(int to, long dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, i));
            graph[b].add(new Node(a, i));
        }

        long[] d = new long[N + 1];
        Arrays.fill(d, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.dist));
        pq.offer(new Node(1, 0));
        d[1] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (d[cur.to] < cur.dist) continue;
            for (Node nxt : graph[cur.to]) {
                long nxtDist = cur.dist + ((nxt.dist - cur.dist) % M + M) % M;
                if (d[nxt.to] <= nxtDist + 1) continue;
                d[nxt.to] = nxtDist + 1;
                pq.offer(new Node(nxt.to, nxtDist + 1));
            }
        }
        System.out.println(d[N]);
    }
}