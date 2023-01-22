import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 1000000005;
    static int N, M;
    static List<Bridge>[] graph;

    static class Bridge {
        int to;
        int minWeight;

        public Bridge(int to, int minWeight) {
            this.to = to;
            this.minWeight = minWeight;
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
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A].add(new Bridge(B, C));
            graph[B].add(new Bridge(A, C));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] d = new int[N + 1];
        PriorityQueue<Bridge> pq = new PriorityQueue<>((o1, o2) -> o2.minWeight - o1.minWeight);
        pq.offer(new Bridge(start, INF));
        d[start] = INF;
        while (!pq.isEmpty()) {
            Bridge cur = pq.poll();
            if (d[cur.to] > cur.minWeight) continue;
            for (Bridge nxt : graph[cur.to]) {
                int minWeight = Math.min(cur.minWeight, nxt.minWeight);
                if (d[nxt.to] < minWeight) {
                    pq.offer(new Bridge(nxt.to, minWeight));
                    d[nxt.to] = minWeight;
                }
            }
        }

        System.out.println(d[end]);
    }
}