import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, C;
    static int[] homes = new int[200000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            homes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(homes, 0, N);
        int l = 1;
        int r = 1_000_000_000;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (solve(mid)) l = mid;
            else r = mid - 1;
        }
        System.out.println(l);
    }

    static boolean solve(int dist) {
        int cnt = 1;
        int lastPos = homes[0];
        for (int i = 1; i < N; i++) {
            if (homes[i] >= lastPos + dist) {
                cnt++;
                lastPos = homes[i];
            }
        }
        return cnt >= C;
    }
}