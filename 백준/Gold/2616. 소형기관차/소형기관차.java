import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] prefixSum = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken());
        }
        int K = Integer.parseInt(br.readLine());

        int[][] memo = new int[4][N + 1];
        for (int i = 1; i <= 3; i++) {
            for (int j = K * i; j <= N; j++) {
                memo[i][j] = Math.max(memo[i][j - 1], memo[i - 1][j - K] + prefixSum[j] - prefixSum[j - K]);
            }
        }
        System.out.println(memo[3][N]);
    }
}
