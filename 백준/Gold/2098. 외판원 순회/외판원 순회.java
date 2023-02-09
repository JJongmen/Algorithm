import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 100000000;
    static int VISIT_ALL;
    static int N;
    static int[][] cost = new int[16][16];
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        VISIT_ALL = (1 << N) - 1;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        memo = new int[N][1 << N];

        System.out.println(tsp(0, 1));
    }

    private static int tsp(int cur, int status) {
        if (status == VISIT_ALL) {
            if (cost[cur][0] == 0) return INF;
            return cost[cur][0];
        }

        if (memo[cur][status] > 0) {
            return memo[cur][status];
        }
        memo[cur][status] = INF;

        for (int nxt = 0; nxt < N; nxt++) {
            if (cost[cur][nxt] == 0) continue;
            if ((status & (1 << nxt)) > 0) continue;
            memo[cur][status] = Math.min(memo[cur][status], cost[cur][nxt] + tsp(nxt, status | (1 << nxt)));
        }

        return memo[cur][status];
    }
}