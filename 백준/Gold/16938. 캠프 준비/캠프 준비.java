import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = 1000000005;
    static int N, L, R, X;
    static int[] problems = new int[20];
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            problems[i] = Integer.parseInt(st.nextToken());
        }

        func(1, 0, 0, INF, 0);
        System.out.println(result);
    }

    private static void func(int cur, int cnt, int sum, int min, int max) {
        if (cur == N + 1) {
            if (cnt >= 2 && L <= sum && sum <= R && max - min >= X) result++;
            return;
        }
        // cur번째 문제 선택 X
        func(cur + 1, cnt, sum, min, max);
        // cur번째 문제 선택 O
        func(cur + 1, cnt + 1, sum + problems[cur], Math.min(min, problems[cur]), Math.max(max, problems[cur]));
    }
}