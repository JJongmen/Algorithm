import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] memo = new int[N + 2];
        int max = 0;
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            memo[i] = Math.max(memo[i], max);
            max = Math.max(max, memo[i]);
            if (i + time > N + 1) {
                continue;
            }
            memo[i + time] = Math.max(memo[i + time], memo[i] + price);
        }

        System.out.println(Math.max(max, memo[N + 1]));
    }
}