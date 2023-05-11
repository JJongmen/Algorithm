import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        long[][] memo = new long[N + 1][2];
        memo[1][0] = 0;
        memo[1][1] = 1;
        for (int i = 2; i <= N; i++) {
            memo[i][0] = memo[i - 1][0] + memo[i - 1][1];
            memo[i][1] = memo[i - 1][0];
        }
        System.out.println(memo[N][0] + memo[N][1]);
    }
}