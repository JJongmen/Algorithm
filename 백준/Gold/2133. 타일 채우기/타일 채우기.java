import java.util.Scanner;

public class Main {

    static int[] memo = new int[31];

    static int solve(int n) {
        if (n % 2 == 1) {
            return 0;
        }
        memo[0] = 1;
        memo[2] = 3;
        int sum = 1;    // 1 + f(2) + f(4) + ...
        for (int i = 2; i <= n - 2; i += 2) {
            memo[i + 2] = memo[i] * 3 + sum * 2;
            sum += memo[i];
        }
        return memo[n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(solve(in.nextInt()));
    }
}