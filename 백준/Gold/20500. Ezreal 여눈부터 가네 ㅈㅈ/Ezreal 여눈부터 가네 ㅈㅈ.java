import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        int[][] memo = new int[N + 1][3];
        memo[1][2] = 1;
        for (int i = 2; i <= N; i++) {
            memo[i][0] = (memo[i - 1][1] + memo[i - 1][2]) % 1000000007;
            memo[i][1] = (memo[i - 1][0] + memo[i - 1][2]) % 1000000007;
            memo[i][2] = (memo[i - 1][0] + memo[i - 1][1]) % 1000000007;
        }

        System.out.println(memo[N][0]);
    }
}