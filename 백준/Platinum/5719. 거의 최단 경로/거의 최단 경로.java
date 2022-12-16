import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 1000000;
    static int N, M;
    static int S, D;
    static List<Node>[] graph;
    static int[] d;
    static List<Integer>[] pre;
    static boolean[] isRemoved;

    static class Node {
        int to;
        int dist;

        public Node(int to) {
            this.to = to;
        }

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return to == node.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(to);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            graph = new List[N];
            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());
                graph[U].add(new Node(V, P));
            }

            dijkstra();
            isRemoved = new boolean[N];
            removeRoute(D);
            dijkstra();
            bw.write(d[D] == INF ? "-1\n" : d[D] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dijkstra() {
        d = new int[N];
        Arrays.fill(d, INF);
        pre = new List[N];
        for (int i = 0; i < N; i++) {
            pre[i] = new ArrayList<>();
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        pq.offer(new Node(S, 0));
        d[S] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (d[cur.to] != cur.dist) continue;
            for (Node nxt : graph[cur.to]) {
                int nxtDist = d[cur.to] + nxt.dist;
                if (d[nxt.to] < nxtDist) continue;
                if (d[nxt.to] > nxtDist) {
                    pre[nxt.to].clear();
                    pq.offer(new Node(nxt.to, nxtDist));
                }
                pre[nxt.to].add(cur.to);
                d[nxt.to] = nxtDist;
            }
        }
    }

    // cur 위치로 가는 경로를 삭제한다.
    private static void removeRoute(int cur) {
        if (cur == S) return;
        isRemoved[cur] = true;
        for (Integer back : pre[cur]) {
            graph[back].remove(new Node(cur));
            if (isRemoved[back]) continue;
            removeRoute(back);
        }
    }
}