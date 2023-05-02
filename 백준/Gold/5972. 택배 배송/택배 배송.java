import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int INF = 50000005;
    static int N, M;
    static List<Node>[] graph = new List[50001];
    static int[] dist = new int[50001];

    static class Node {
        int to, dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A].add(new Node(B, C));
            graph[B].add(new Node(A, C));
        }


        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        pq.offer(new Node(1, 0));
        Arrays.fill(dist, 2, N + 1, INF);
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.dist > dist[cur.to]) continue;
            for (Node nxt : graph[cur.to]) {
                int nxtDist = nxt.dist + dist[cur.to];
                if (nxtDist >= dist[nxt.to]) continue;
                pq.offer(new Node(nxt.to, nxtDist));
                dist[nxt.to] = nxtDist;
            }
        }
        System.out.println(dist[N]);
    }
}