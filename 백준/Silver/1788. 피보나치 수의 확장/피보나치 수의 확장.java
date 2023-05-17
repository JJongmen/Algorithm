import java.util.Scanner;

public class Main {

    public static final int MOD = 1000000000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] memo = new int[1000001];
        if (N > 0) {
            memo[0] = 0;
            memo[1] = 1;
            for (int i = 2; i <= N; i++) {
                memo[i] = memo[i - 1] + memo[i - 2];
                memo[i] %= MOD;
            }
        } else {
            memo[0] = 0;
            memo[1] = 1;
            for (int i = 2; i <= -N; i++) {
                memo[i] = memo[i - 2] - memo[i - 1];
                if (memo[i] < 0) {
                    memo[i] = -((-memo[i]) % MOD);
                } else {
                    memo[i] %= MOD;
                }
            }
        }
        if (N >= 0) {
            if (memo[N] == 0) {
                System.out.println(0);
            } else if (memo[N] > 0) {
                System.out.println(1);
            } else {
                System.out.println(-1);
            }
            System.out.println(Math.abs(memo[N]));
        } else {
            if (memo[-N] == 0) {
                System.out.println(0);
            } else if (memo[-N] > 0) {
                System.out.println(1);
            } else {
                System.out.println(-1);
            }
            System.out.println(Math.abs(memo[-N]));
        }
    }
}