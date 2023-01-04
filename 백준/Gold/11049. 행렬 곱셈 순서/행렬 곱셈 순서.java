import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = Integer.MAX_VALUE;
    static int N;
    static int[][] matrix = new int[501][2];
    static int[][] memo = new int[501][501];    // memo[i][j] : i번 행렬부터 j번 행렬까지의 곱셈 연산 횟수의 최솟값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int k = 1; k < N; k++) {
            for (int i = 1; i + k <= N; i++) {
                memo[i][i + k] = INF;
                for (int j = i; j < i + k; j++) {
                    memo[i][i + k] = Math.min(memo[i][i + k],
                            memo[i][j] + memo[j + 1][i + k] + matrix[i][0] * matrix[j][1] * matrix[i + k][1]);
                }
            }
        }
        System.out.println(memo[1][N]);
    }
}