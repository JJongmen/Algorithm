import java.util.Scanner;

public class Main {
    public static final int MOD = 1000000000;
    static int[][][] memo;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        memo = new int[N + 1][10][1024];
        for (int i = 1; i <= 9; i++) {
            memo[1][i][1 << i] = 1;
        }

        for (int i = 0; i < N; i++) {
            for (int k = 0; k < 1024; k++) {
                memo[i + 1][1][k | (1 << 1)] += memo[i][0][k];
                memo[i + 1][1][k | (1 << 1)] %= MOD;
                memo[i + 1][8][k | (1 << 8)] += memo[i][9][k];
                memo[i + 1][8][k | (1 << 8)] %= MOD;
            }
            for (int j = 1; j <= 8; j++) {
                for (int k = 0; k < 1024; k++) {
                    memo[i + 1][j - 1][k | (1 << (j - 1))] += memo[i][j][k];
                    memo[i + 1][j - 1][k | (1 << (j - 1))] %= MOD;
                    memo[i + 1][j + 1][k | (1 << (j + 1))] += memo[i][j][k];
                    memo[i + 1][j + 1][k | (1 << (j + 1))] %= MOD;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += memo[N][i][1023];
            result %= MOD;
        }
        System.out.println(result);
    }

}