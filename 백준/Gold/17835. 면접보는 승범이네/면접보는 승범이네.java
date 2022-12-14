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
    public static final long INF = 1_000_000_000_005L;
    static int N, M, K;
    static List<Node>[] graph;

    static class Node {
        int num;
        long dist;

        public Node(int num, long dist) {
            this.num = num;
            this.dist = dist;
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
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[V].add(new Node(U, C));
        }

        long[] d = new long[N + 1];
        Arrays.fill(d, INF);        
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.dist));
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken());
            pq.offer(new Node(num, 0));
            d[num] = 0;
        }
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (d[cur.num] != cur.dist) continue;
            for (Node nxt : graph[cur.num]) {
                long nxtDist = d[cur.num] + nxt.dist;
                if (d[nxt.num] <= nxtDist) continue;
                d[nxt.num] = nxtDist;
                pq.offer(new Node(nxt.num, nxtDist));
            }
        }

        int maxNum = 0;
        long maxDist = -1;
        for (int num = 1; num <= N; num++) {
            if (maxDist < d[num]) {
                maxNum = num;
                maxDist = d[num];
            }
        }
        System.out.println(maxNum);
        System.out.println(maxDist);
    }
}