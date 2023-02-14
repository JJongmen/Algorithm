import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 1000000005;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] times = new int[N];
        long min = INF;
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(br.readLine());
            min = Math.min(min, times[i]);
        }

        long left = min;
        long right = min * M;
        while (left < right) {
            long mid = (left + right) / 2;

            long cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += mid / times[i];
            }

            if (cnt < M) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(left);
    }
}