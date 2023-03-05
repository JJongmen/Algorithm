import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] board = new int[1005][1005];
    static int[][][] memo = new int[1005][1005][3]; // (최대 가치, 왼쪽방향, 오른쪽방향)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= M; i++) {
            memo[1][i][0] = memo[1][i - 1][0] + board[1][i];
        }
        for (int i = 2; i <= N; i++) {
            memo[i][1][2] = memo[i - 1][1][0] + board[i][1];
            memo[i][M][1] = memo[i - 1][M][0] + board[i][M];
            for (int j = M - 1; j >= 1; j--) {
                memo[i][j][1] = Math.max(memo[i - 1][j][0], memo[i][j + 1][1]) + board[i][j];
            }
            for (int j = 2; j <= M; j++) {
                memo[i][j][2] = Math.max(memo[i - 1][j][0], memo[i][j - 1][2]) + board[i][j];
            }
            for (int j = 1; j <= M; j++) {
                memo[i][j][0] = Math.max(memo[i][j][1], memo[i][j][2]);
            }
        }
        System.out.println(memo[N][M][0]);
    }
}