import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 10_000_000;
    static int N, M, Q;
    static int[] times;
    static int[][] dist;
    static int[][] interrupt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        times = new int[N + 1];
        dist = new int[N + 1][N + 1];
        interrupt = new int[N + 1][N + 1];
        List<Integer> timeIdxes = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            Arrays.fill(interrupt[i], INF);
            dist[i][i] = 0;
            interrupt[i][i] = 0;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            times[i] = Integer.parseInt(st.nextToken());
            timeIdxes.add(i);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            dist[a][b] = dist[b][a] = d;
            interrupt[a][b] = interrupt[b][a] = d + Math.max(times[a], times[b]);
        }

        timeIdxes.sort(Comparator.comparingInt(o -> times[o]));
        for (int k = 0; k < N; k++) {
            int idx = timeIdxes.get(k);
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][idx] + dist[idx][j]);
                    interrupt[i][j] = Math.min(interrupt[i][j],
                            dist[i][j] + Math.max(times[i], Math.max(times[j], times[idx])));
                }
            }
        }

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            bw.write(interrupt[S][T] >= INF ? "-1\n" : interrupt[S][T] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}