import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int INF = 10001;

    public static int solve(int price, int[] coins) {
        Integer[] noDuplicatesCoins = removeDuplicates(coins, price);
        int[] memo = new int[price + 1];
        Arrays.fill(memo, 1, price + 1, INF);
        for (int i = 0; i < noDuplicatesCoins.length; i++) {
            fillMemoTable(price, memo, noDuplicatesCoins[i]);
        }
        return (memo[price] == INF) ? -1 : memo[price];
    }

    private static void fillMemoTable(int price, int[] memo, int coin) {
        for (int i = coin; i <= price; i++) {
            memo[i] = Math.min(memo[i], memo[i - coin] + 1);
        }
    }

    private static Integer[] removeDuplicates(int[] coins, int price) {
        Set<Integer> coinSet = new HashSet<>();
        for (int coin : coins) {
            addSmallerCoin(price, coinSet, coin);
        }
        return coinSet.toArray(new Integer[0]);
    }

    private static void addSmallerCoin(int price, Set<Integer> coinSet, int coin) {
        if (coin <= price) {
            coinSet.add(coin);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solve(k, coins));
    }
}