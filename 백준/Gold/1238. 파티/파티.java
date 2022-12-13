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
    public static final int INF = 10000000;
    static int N, M, X;
    static int[] go, back;
    static List<Node>[] forwardGraph;
    static List<Node>[] reverseGraph;

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
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        forwardGraph = new List[N + 1];
        reverseGraph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            forwardGraph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            forwardGraph[a].add(new Node(b, t));
            reverseGraph[b].add(new Node(a, t));
        }

        go = new int[N + 1];
        back = new int[N + 1];
        Arrays.fill(go, INF);
        Arrays.fill(back, INF);

        getDist(reverseGraph, back);
        getDist(forwardGraph, go);
        int result = 0;
        for (int i = 1; i <= N; i++) {
            int dist = go[i] + back[i];
            result = Math.max(result, dist);
        }
        System.out.println(result);
    }

    private static void getDist(List<Node>[] graph, int[] d) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        pq.offer(new Node(X, 0));
        d[X] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (d[cur.num] != cur.dist) {
                continue;
            }
            for (Node nxt : graph[cur.num]) {
                int nxtDist = d[cur.num] + nxt.dist;
                if (d[nxt.num] <= nxtDist) {
                    continue;
                }
                d[nxt.num] = nxtDist;
                pq.offer(new Node(nxt.num, nxtDist));
            }
        }
    }
}