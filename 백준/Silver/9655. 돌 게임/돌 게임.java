import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] memo = new int[1001];
        memo[1] = 1;
        memo[2] = 2;
        memo[3] = 1;
        for (int i = 4; i <= N; i++) {
            memo[i] = Math.max(memo[i - 1] + 1, memo[i - 3] + 1);
        }
        System.out.println(memo[N] % 2 == 0 ? "CY" : "SK");
    }

}