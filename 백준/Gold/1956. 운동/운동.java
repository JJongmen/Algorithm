import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static int[][] d = new int[401][401];

    public static final int INF = 10_000_000;

    static {
        for (int i = 1; i <= 400; i++) {
            Arrays.fill(d[i], INF);
            d[i][i] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            d[a][b] = c;
        }

        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        int result = INF;
        for (int i = 1; i < V; i++) {
            for (int j = i + 1; j <= V; j++) {
                if (d[i][j] != INF && d[j][i] != INF) {
                    result = Math.min(result, d[i][j] + d[j][i]);
                }
            }
        }
        System.out.println(result == INF ? -1 : result);
    }
}