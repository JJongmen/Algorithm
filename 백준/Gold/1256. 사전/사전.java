import java.util.Scanner;

public class Main {
    public static final int INF = 1000000005;
    static int N, M, K;
    static int[][] memo = new int[201][101];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        K = scan.nextInt();

        if (comb(N + M, N) < K) {
            System.out.println(-1);
            return;
        }

        StringBuilder result = new StringBuilder();
        while (N != 0 && M != 0) {
            long mid = comb(N + M - 1, N - 1);
            if (K <= mid) {
                result.append("a");
                N--;
            } else {
                result.append("z");
                M--;
                K -= mid;
            }
        }
        if (N != 0) {
            result.append("a".repeat(N));
        }
        if (M != 0) {
            result.append("z".repeat(M));
        }

        System.out.println(result);
    }

    private static int comb(int n, int r) {
        if (memo[n][r] != 0) {
            return memo[n][r];
        }
        if (r == 0 || n == r) {
            return 1;
        }
        if (r == 1 || r == n - 1) {
            return n;
        }
        int value = comb(n - 1, r - 1) + comb(n - 1, r);
        if (value > 1000000000) {
            value = INF;
        }
        return memo[n][r] = memo[n][n - r] = value;
    }
}