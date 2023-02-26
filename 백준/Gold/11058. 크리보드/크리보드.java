import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        long[] memo = new long[N + 1];
        memo[1] = 1;
        for (int i = 2; i <= N; i++) {
            memo[i] = memo[i - 1] + 1;
            for (int j = 3; i - j >= 1; j++) {
                memo[i] = Math.max(memo[i], memo[i - j] * (j - 1));
            }
        }
        System.out.println(memo[N]);
    }
}