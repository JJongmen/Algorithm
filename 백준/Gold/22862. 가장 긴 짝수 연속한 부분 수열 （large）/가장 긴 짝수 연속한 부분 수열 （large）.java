import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] seq = new int[1000005];
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int r = 0;
        int oddCnt = 0;
        for (int l = 0; l < N; l++) {
            while (r < N && oddCnt <= K) {
                if (seq[r] % 2 == 1) {
                    if (oddCnt == K) break;
                    oddCnt++;
                }
                r++;
            }
            result = Math.max(result, r - l - oddCnt);
            if (r == N) break;
            if (seq[l] % 2 == 1) oddCnt--;
        }
        System.out.println(result);
    }
}