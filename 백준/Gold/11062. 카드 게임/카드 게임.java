import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] cards = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
            }

            int[][] memo = new int[N + 1][N + 1];
            if (N % 2 == 1) {
                for (int i = 1; i <= N; i++) {
                    memo[i][i] = cards[i];
                }
            }
            for (int k = 1; k < N; k++) {
                for (int i = 1; i + k <= N; i++) {
                    // 근우가 뽑을 차례
                    if ((N % 2 == 0 && k % 2 == 1) || (N % 2 == 1 && k % 2 == 0)) {
                        memo[i][i + k] = Math.max(cards[i] + memo[i + 1][i + k], cards[i + k] + memo[i][i + k - 1]);
                    }
                    // 명우가 뽑을 차례
                    else {
                        memo[i][i + k] = Math.min(memo[i + 1][i + k], memo[i][i + k - 1]);
                    }
                }
            }
            bw.write(memo[1][N] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}