import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = 999999999;
    static int[] nums;
    static int[] sums;
    static int[][] memo;

    private static int solve(int start, int end) {
        if (memo[start][end] != INF) {
            return memo[start][end];
        }
        if (start == end) {
            return memo[start][end] = 0;
        }

        int min = INF;
        for (int i = start; i + 1 <= end; i++) {
            min = Math.min(min, solve(start, i) + solve(i + 1, end)
                    + sums[end] - sums[start - 1]);
        }
        return memo[start][end] = min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            nums = new int[K + 1];
            sums = new int[K + 1];
            memo = new int[K + 1][K + 1];
            for (int i = 0; i < K + 1; i++) {
                Arrays.fill(memo[i], INF);
            }
            for (int i = 1; i <= K; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
                sums[i] = sums[i - 1] + nums[i];
            }

            solve(1, K);

            bw.write(memo[1][K] + "\n");
        }

        bw.flush();
        br.close();
        bw.close();

    }
}