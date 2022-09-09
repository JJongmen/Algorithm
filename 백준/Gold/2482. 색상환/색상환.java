import java.util.Scanner;

public class Main {
    static int[][] memo;

    static int comb(int n, int k) {
        if (memo[n][k] != 0) {
            return memo[n][k];
        }
        if (k > n - k)
            return memo[n][k] = comb(n, n - k);
        if (k == 0) {
            return memo[n][0] = 1;
        } else if (k == 1) {
            return memo[n][1] = n;
        }
        return memo[n][k] = (comb(n - 1, k) + comb(n - 1, k - 1)) % 1000000003;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int K = scan.nextInt();

        if (2 * K > N) {
            System.out.println(0);
            return;
        } else if (K == 1) {
            System.out.println(N);
            return;
        }

        memo = new int[N - K + 1][K + 1];
        System.out.println((comb(N-K,K) + comb(N-K-1,K-1)) % 1000000003);
    }
}