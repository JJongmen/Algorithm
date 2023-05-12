import java.util.Scanner;

public class Main {

    public static final int MOD = 10007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[][] memo = new int[N + 1][10];
        memo[0][0] = 1;
        for (int i = 1; i <= N; i++) {
            memo[i][0] = memo[i - 1][0];
            for (int j = 1; j <= 9; j++) {
                memo[i][j] = (memo[i][j - 1] + memo[i - 1][j]) % MOD;
            }
        }

        int result = 0;
        for (int i = 0; i <= 9; i++) {
            result += memo[N][i];
        }
        result %= MOD;
        System.out.println(result);
    }
}