import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 10000;
    static int N, M, R;
    static int[] itemCnt = new int[101];
    static int[][] dist = new int[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
            itemCnt[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            dist[a][b] = l;
            dist[b][a] = l;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int result = 0;
        for (int start = 1; start <= N; start++) {
            int cnt = 0;
            for (int end = 1; end <= N; end++) {
                if (dist[start][end] <= M) cnt += itemCnt[end];
            }
            result = Math.max(result, cnt);
        }
        System.out.println(result);
    }
}