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
    public static final int INF = 100000000;
    static int N, E;
    static List<Node>[] graph;

    static class Node {
        int num;
        int dist;

        public Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        int[] d1 = getDist(v1);
        int[] d2 = getDist(v2);

        int result = Math.min(d1[1] + d1[v2] + d2[N], d2[1] + d2[v1] + d1[N]);
        System.out.println(result >= INF ? -1 : result);
    }

    private static int[] getDist(int v) {
        int[] d = new int[N + 1];  // d[i] = v -> i 까지의 최단거리
        Arrays.fill(d, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        pq.offer(new Node(v, 0));
        d[v] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (d[cur.num] != cur.dist) continue;
            for (Node nxt : graph[cur.num]) {
                int nxtDist = d[cur.num] + nxt.dist;
                if (d[nxt.num] <= nxtDist) continue;
                d[nxt.num] = nxtDist;
                pq.offer(new Node(nxt.num, nxtDist));
            }
        }
        return d;
    }
}