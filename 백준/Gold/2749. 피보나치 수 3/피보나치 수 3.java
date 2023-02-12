import java.util.Scanner;

public class Main {

    public static final int MOD = 1000000;
    public static final int PERIOD = MOD / 10 * 15;

    static int[] memo = new int[PERIOD];

    static {
        memo[0] = 0;
        memo[1] = 1;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long N = scan.nextLong();

        for (int i = 2; i < PERIOD; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
            memo[i] %= MOD;
        }
        System.out.println(memo[(int) (N % PERIOD)]);
    }
}