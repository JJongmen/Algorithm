import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static List<Node>[] graph;
    static PriorityQueue<Integer>[] d;

    static class Node {
        int to;
        int dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
        }

        d = new PriorityQueue[N + 1];
        for (int i = 1; i <= N; i++) {
            d[i] = new PriorityQueue<>(Comparator.reverseOrder());
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        pq.offer(new Node(1, 0));
        d[1].offer(0);
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            for (Node nxt : graph[cur.to]) {
                int nxtDist = cur.dist + nxt.dist;
                if (d[nxt.to].size() == K) {
                    if (nxtDist >= d[nxt.to].peek()) continue;
                    d[nxt.to].poll();
                }
                d[nxt.to].offer(nxtDist);
                pq.offer(new Node(nxt.to, nxtDist));
            }
        }
        for (int i = 1; i <= N; i++) {
            bw.write(d[i].size() == K ? d[i].peek() + "\n" : "-1\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}