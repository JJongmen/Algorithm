import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        int[] memo = new int[25];
        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i <= N; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        System.out.println(memo[N]);
    }
}