import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int solve(int price, int[] coins) {
        int[][] memo = new int[price + 1][coins.length + 1];
        for (int i = 1; i <= coins.length; i++) {
            memo[0][i] = 1;
        }

        for (int j = 1; j <= coins.length; j++) {
            for (int i = 1; i <= price; i++) {
                memo[i][j] = memo[i][j - 1];
                if (i - coins[j - 1] >= 0) {
                    memo[i][j] += memo[i - coins[j - 1]][j];
                }
            }
        }
        return memo[price][coins.length];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] coins = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int M = Integer.parseInt(br.readLine());

            bw.write(solve(M, coins) + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}