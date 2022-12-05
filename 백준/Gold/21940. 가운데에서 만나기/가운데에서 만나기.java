import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 10_000_000;
    static int N, M, K;
    static int[][] d = new int[201][201];
    static List<Integer> homes = new ArrayList<>();
    static int[] times = new int[201];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            Arrays.fill(d[i], 1, N + 1, INF);
            d[i][i] = 0;
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            d[A][B] = T;
        }
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= K; i++) {
            homes.add(Integer.parseInt(st.nextToken()));
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        int minTime = INF;
        for (int city = 1; city <= N; city++) {
            for (Integer home : homes) {
                times[city] = Math.max(times[city], d[home][city] + d[city][home]);
            }
            minTime = Math.min(minTime, times[city]);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (times[i] == minTime) {
                result.append(i).append(" ");
            }
        }
        System.out.println(result);
    }
}