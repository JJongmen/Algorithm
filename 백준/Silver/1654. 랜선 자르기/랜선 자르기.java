import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] lines = new int[10000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            lines[i] = Integer.parseInt(br.readLine());
        }

        long l = 0;
        long r = (1 << 31) - 1;
        while (l < r) {
            long mid = (l + r + 1) / 2;
            if (solve(mid)) l = mid;
            else r = mid - 1;
        }
        System.out.println(l);
    }

    static boolean solve(long length) {
        long cnt = 0;
        for (int i = 0; i < K; i++) {
            cnt += lines[i] / length;
        }
        return cnt >= N;
    }
}