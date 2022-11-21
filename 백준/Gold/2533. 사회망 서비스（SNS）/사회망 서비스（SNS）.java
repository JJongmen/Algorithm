import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int result;
    static List<List<Integer>> tree = new ArrayList<>();
    /**
     * dp[i][j]
     * i번 정점이 (얼리 어답터가 아닌 경우, 얼리 어답터인 경우)에
     * i번 정점을 루트로 가지는 서브트리에서 필요한 얼리 아답터의 최소 수
     */
    static int[][] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        init();
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        dfs(1, 0);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void init() {
        dp = new int[N + 1][2];
        for (int i = 0; i <= N; i++) {
            tree.add(new LinkedList<>());
            dp[i][1] = 1;
        }
    }

    private static void dfs(int cur, int p) {
        for (Integer nxt : tree.get(cur)) {
            if (nxt == p) continue;
            dfs(nxt, cur);
            dp[cur][0] += dp[nxt][1];
            dp[cur][1] += Math.min(dp[nxt][0], dp[nxt][1]);
        }
    }
}