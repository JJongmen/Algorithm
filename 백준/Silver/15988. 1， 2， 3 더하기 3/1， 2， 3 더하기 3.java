import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final int MAX = 1000000;
    static long[] memo = new long[MAX + 1];

    public static final int MOD = 1_000_000_009;

    static {
        memo[1] = 1;
        memo[2] = 2;
        memo[3] = 4;
        for (int i = 4; i <= MAX; i++) {
            memo[i] = memo[i - 3] + memo[i - 2] + memo[i - 1];
            memo[i] %= MOD;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(memo[n]).append('\n');
        }
        System.out.println(sb);
    }
}