import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int K = scan.nextInt();
        solve(N, K);
    }

    private static void solve(int N, int K) {
        if (K > (N / 2) * (N - N / 2)) {
            System.out.println(-1);
            return;
        }
        if (K == 0) {
            System.out.println("B".repeat(N - 1) + "A");
            return;
        }
        if (K == 1) {
            System.out.println("B".repeat(N - 2) + "AB");
            return;
        }

        int i = 2;
        int expr = 1;
        for (; (expr = (i / 2) * (i - i / 2)) < K; i++) {
        }
        i--;
        expr = (i / 2) * (i - i / 2);
        String answer = "B".repeat(N - i - 1) + "A".repeat(i / 2)
                + "B".repeat(i - i / 2 - (K - expr)) + "A".repeat(1)
                + "B".repeat(K - expr);
        System.out.println(answer);
    }
}