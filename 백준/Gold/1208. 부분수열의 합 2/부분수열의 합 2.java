import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static final int ZERO = 2000_000;
    
    static int N, S;
    static int[] arr = new int[45];
    static int[] counts = new int[ZERO * 2 + 1];
    static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        countHalf(0, 0);
        countHalfAndMatch(N / 2, 0);
        if (S == 0) {
            result--;
        }
        System.out.println(result);
    }

    private static void countHalfAndMatch(int idx, int sum) {
        if (idx == N) {
            int other = S - sum + ZERO;
            if (0 <= other && other <= ZERO * 2) {
                result += counts[other];
            }
            return;
        }
        countHalfAndMatch(idx + 1, sum);
        countHalfAndMatch(idx + 1, arr[idx] + sum);
    }

    private static void countHalf(int idx, int sum) {
        if (idx == N / 2) {
            counts[sum + ZERO]++;
            return;
        }
        countHalf(idx + 1, sum);
        countHalf(idx + 1, arr[idx] + sum);
    }
}