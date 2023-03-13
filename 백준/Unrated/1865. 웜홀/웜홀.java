import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, W;
    static Road[] roads;
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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            roads = new Road[2 * M + W];
            for (int i = 0; i < 2 * M; i += 2) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                roads[i] = new Road(S, E, T);
                roads[i + 1] = new Road(E, S, T);
            }
            for (int i = 2 * M; i < 2 * M + W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                roads[i] = new Road(S, E, -T);
            }

            if (bellman_ford()) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean bellman_ford() {
        long[] dist = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            for (Road road : roads) {
                long nxtDist = dist[road.st] + road.time;
                if (dist[road.en] <= nxtDist) continue;
                if (i == N) return true;
                dist[road.en] = nxtDist;
            }
        }
        return false;
    }
}