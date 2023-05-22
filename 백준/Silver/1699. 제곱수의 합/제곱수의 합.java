import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] memo = new int[100001];

    public static final int INF = 1000000;

    static {
        Arrays.fill(memo, INF);
        memo[0] = 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int num = 1;
        for (int i = 1; i <= N; i++) {
            if ((num + 1) * (num + 1) <= i) num++;
            for (int j = 1; j <= num; j++) {
                memo[i] = Math.min(memo[i], 1 + memo[i - j * j]);
            }
        }
        System.out.println(memo[N]);
    }
}