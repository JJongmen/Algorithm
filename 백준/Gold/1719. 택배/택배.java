import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] d = new int[201][201];
    static int[][] nxt = new int[201][201];

    public static final int INF = 10_000_000;

    static {
        for (int i = 1; i <= 200; i++) {
            Arrays.fill(d[i], INF);
            d[i][i] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            d[a][b] = t;
            d[b][a] = t;
            nxt[a][b] = b;
            nxt[b][a] = a;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    int time = d[i][k] + d[k][j];
                    if (d[i][j] > time) {
                        d[i][j] = time;
                        nxt[i][j] = nxt[i][k];
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    sb.append("- ");
                } else {
                    sb.append(nxt[i][j]).append(" ");
                }
            }
            bw.write(sb + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}