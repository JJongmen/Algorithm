import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, L;
    static int[] restAreas = new int[55];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        if (N > 0) {
            st = new StringTokenizer(br.readLine());
        }
        for (int i = 0; i < N; i++) {
            restAreas[i] = Integer.parseInt(st.nextToken());
        }
        restAreas[N] = 0;
        restAreas[N + 1] = L;

        Arrays.sort(restAreas, 0, N + 2);
        int l = 0;
        int r = L / 2;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (solve(mid)) l = mid;
            else r = mid - 1;
        }
        System.out.println(l + 1);
    }

    static boolean solve(int interval) {
        int cnt = 0;
        for (int i = 0; i <= N; i++) {
            int dist = restAreas[i + 1] - restAreas[i];
            cnt += dist / interval;
            if (dist % interval == 0) cnt--;
        }
        return cnt >= M + 1;
    }
}