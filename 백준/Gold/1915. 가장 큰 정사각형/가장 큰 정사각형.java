import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[][] board = new int[1005][1005];
        int[][] memo = new int[1005][1005];
        for (int i = 1; i <= n; i++) {
            String str = scan.next();
            for (int j = 1; j <= m; j++) {
                board[i][j] = str.charAt(j - 1) - '0';
            }
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (board[i][j] == 0) continue;
                memo[i][j] = Math.min(memo[i - 1][j - 1],
                        Math.min(memo[i][j - 1], memo[i - 1][j])) + 1;
                result = Math.max(result, memo[i][j] * memo[i][j]);
            }
        }
        System.out.println(result);
    }
}