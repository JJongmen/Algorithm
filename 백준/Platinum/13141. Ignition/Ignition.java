import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 10000000;
    static int N, M;
    static int[][] ori;
    static int[][] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        ori = new int[N + 1][N + 1];
        d = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(d[i], INF);
            d[i][i] = 0;
        }

        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            d[b][a] = d[a][b] = Math.min(d[a][b], c);
            ori[b][a] = ori[a][b] = Math.max(ori[a][b], c);
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i < N; i++) {
                for (int j = i + 1; j <= N; j++) {
                    d[j][i] = d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        double result = INF;
        for (int s = 1; s <= N; s++) {
            double max = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    max = Math.max(max, Math.max(d[s][i], d[s][j]) + (ori[i][j] - Math.abs(d[s][i] - d[s][j])) / 2D);
                }
            }
            result = Math.min(result, max);
        }
        System.out.println(result);
    }
}