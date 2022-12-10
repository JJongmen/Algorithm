import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 100_000_000;
    static int N, Q;
    static int[][][] d; // d[i][k][k] : i에서 j로 갈 때 경유한 집의 번호가 k 이하일 때의 최단시간

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        d = new int[N + 1][N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int time = Integer.parseInt(st.nextToken());
                if (time == 0 && i != j) {
                    Arrays.fill(d[i][j], INF);
                } else {
                    Arrays.fill(d[i][j], time);
                }
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i < N; i++) {
                for (int j = i + 1; j <= N; j++) {
                    d[j][i][k] = d[i][j][k] = Math.min(d[i][j][k - 1], d[i][k][k - 1] + d[k][j][k - 1]);
                }
            }
        }

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            bw.write(d[s][e][C - 1] == INF ? "-1\n" : d[s][e][C - 1] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}