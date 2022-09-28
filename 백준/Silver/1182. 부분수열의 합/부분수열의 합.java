import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int cnt = 0;
    private static int N;
    private static int S;
    private static int[] nums = new int[21];

    private static void bt(int cur, int sum) {
        if (cur == N) {
            if (sum == S) cnt++;
            return;
        }
        bt(cur + 1, sum);
        bt(cur + 1, sum + nums[cur]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        bt(0, 0);
        System.out.println(S == 0 ? cnt - 1 : cnt);
    }
}