import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static Coin[] coins;
    private static int[][] memo;

    private static class Coin {
        int price;
        int cnt;

        public Coin(int price, int cnt) {
            this.price = price;
            this.cnt = cnt;
        }
    }

    private static int solve(int price, int kindCnt) {
        if (price < 0) {
            return 0;
        }
        if (memo[price][kindCnt] != -1) {
            return memo[price][kindCnt];
        }
        int sum = 0;
        for (int i = 0; i <= coins[kindCnt].cnt; i++) {
            sum += solve(price - coins[kindCnt].price * i, kindCnt - 1);
        }
        return memo[price][kindCnt] = sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        coins = new Coin[k + 1];
        
        for (int i = 1; i <= k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            coins[i] = new Coin(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        memoInit(T, k);
        System.out.println(solve(T, k));

    }

    private static void memoInit(int T, int k) {
        memo = new int[T + 1][k + 1];
        for (int i = 0; i <= k; i++) {
            memo[0][i] = 1;
        }
        for (int i = 1; i <= T; i++) {
            Arrays.fill(memo[i], -1);
        }
        for (int i = 1; i <= T; i++) {
            memo[i][0] = 0;
        }
    }
}