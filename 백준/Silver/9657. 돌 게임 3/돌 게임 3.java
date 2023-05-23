import java.util.Scanner;

public class Main {
    static boolean[] memo = new boolean[1001];  // 상근이가 이길 경우 true

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        memo[1] = memo[3] = memo[4] = true;
        for (int i = 5; i <= N; i++) {
            if (!memo[i - 1] || !memo[i - 3] || !memo[i - 4])
                memo[i] = true;
        }
        System.out.println(memo[N] ? "SK" : "CY");
    }
}