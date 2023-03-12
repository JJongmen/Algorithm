import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q11657 {
    public static final long INF = 30000000005L;
    static int N, M;
    static Road[] roads;
    static long[] dist;

    static class Road {
        int st, en, time;

        public Road(int st, int en, int time) {
            this.st = st;
            this.en = en;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        roads = new Road[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            roads[i] = new Road(A, B, C);
        }

        dist = new long[N + 1];
        Arrays.fill(dist, 1, N + 1, INF);

        if (bellman_ford(1)) {
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(dist[i] == INF ? -1 : dist[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean bellman_ford(int start) {
        dist[start] = 0;
        for (int i = 1; i <= N; i++) {
            for (Road road : roads) {
                if (dist[road.st] == INF) continue;
                long nxtTime = dist[road.st] + road.time;
                if (dist[road.en] <= nxtTime) continue;
                dist[road.en] = nxtTime;
                if (i == N) return true;
            }
        }
        return false;
    }
}
