import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 10000005;
    static int N, M;
    static int[] sizes;
    static int[] costs;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sizes = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sizes[i] = Integer.parseInt(st.nextToken());
        }

        costs = new int[N + 1];
        int totalCost = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            totalCost += costs[i];
        }

        memo = new int[N + 1][totalCost + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= totalCost; j++) {
                if (j >= costs[i]) {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i - 1][j - costs[i]] + sizes[i]);
                } else {
                    memo[i][j] = memo[i - 1][j];
                }
            }
        }

        for (int i = 0; i <= totalCost; i++) {
            if (memo[N][i] < M) continue;
            System.out.println(i);
            return;
        }
    }
}