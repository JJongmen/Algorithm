import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1106 {
    public static final int INF = 100005;
    static int C, N;
    static int[] cost = new int[25];
    static int[] customer = new int[25];
    static int[] memo = new int[1105];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            customer[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(memo, 1, C + 101, INF);
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= C + 100; j++) {
                if (j < customer[i]) continue;
                memo[j] = Math.min(memo[j], memo[j - customer[i]] + cost[i]);
            }
        }

        int result = INF;
        for (int i = C; i <= C + 100; i++) {
            result = Math.min(result, memo[i]);
        }
        System.out.println(result);
    }
}
