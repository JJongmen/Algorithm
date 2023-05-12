import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] memo = new int[16];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int finish = i + T - 1;
            if (finish > N) continue;
            memo[finish] = Math.max(memo[finish], memo[i - 1] + P);
            for (int j = finish + 1; j <= N; j++) {
                memo[j] = Math.max(memo[j], memo[finish]);
            }
        }

        System.out.println(memo[N]);
    }
}