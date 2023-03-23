import java.util.Scanner;

public class Main {
    public static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int L = sc.nextInt();
        int R = sc.nextInt();

        long[][][] memo = new long[N + 2][N + 2][N + 2];
        memo[1][1][1] = 1;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= Math.min(i, L); j++) {
                for (int k = 1; k <= Math.min(i, R); k++) {
                    memo[i + 1][j + 1][k] += memo[i][j][k];
                    memo[i + 1][j][k] += (i - 1) * memo[i][j][k];
                    memo[i + 1][j][k + 1] += memo[i][j][k];
                    memo[i + 1][j + 1][k] %= MOD;
                    memo[i + 1][j][k] %= MOD;
                    memo[i + 1][j][k + 1] %= MOD;
                }
            }
        }
        System.out.println(memo[N][L][R]);
    }
}