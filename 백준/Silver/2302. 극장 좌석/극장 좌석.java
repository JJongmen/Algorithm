import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, M;
    static int[] memo = new int[41];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        memo[0] = 1;
        memo[1] = 1;
        memo[2] = 2;
        for (int i = 3; i <= N; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }

        M = Integer.parseInt(br.readLine());
        int result = 1;
        int lastVip = 0;
        while (M-- > 0) {
            int vip = Integer.parseInt(br.readLine());
            result *= memo[vip - lastVip - 1];
            lastVip = vip;
        }
        result *= memo[N - lastVip];
        System.out.println(result);
    }
}