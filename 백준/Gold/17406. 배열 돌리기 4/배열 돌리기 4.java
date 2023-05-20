import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] board = new int[51][51];
    static int[][] commands = new int[6][3];
    static boolean[] isUsed = new boolean[6];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int result = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            commands[i][0] = Integer.parseInt(st.nextToken());
            commands[i][1] = Integer.parseInt(st.nextToken());
            commands[i][2] = Integer.parseInt(st.nextToken());
        }

        brute(0);
        System.out.println(result);
    }

    private static void brute(int cur) {
        if (cur == K) {
            for (int i = 1; i <= N; i++) {
                int sum = 0;
                for (int j = 1; j <= M; j++) {
                    sum += board[i][j];
                }
                result = Math.min(result, sum);
            }
            return;
        }
        for (int i = 0; i < K; i++) {
            if (isUsed[i]) continue;
            isUsed[i] = true;
            int[][] original = new int[51][51];
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= M; k++) {
                    original[j][k] = board[j][k];
                }
            }
            turn(i);
            brute(cur + 1);
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= M; k++) {
                    board[j][k] = original[j][k];
                }
            }
            isUsed[i] = false;
        }
    }

    private static void turn(int idx) {
        int[] cmd = commands[idx];
        int r = cmd[0];
        int c = cmd[1];
        int s = cmd[2];
        for (int k = 1; k <= s; k++) {
            int tmp = board[r - k][c + k];
            for (int j = c + k; j > c - k; j--) {
                board[r - k][j] = board[r - k][j - 1];
            }
            for (int i = r - k; i < r + k; i++) {
                board[i][c - k] = board[i + 1][c - k];
            }
            for (int j = c - k; j < c + k; j++) {
                board[r + k][j] = board[r + k][j + 1];
            }
            for (int i = r + k; i > r - k + 1; i--) {
                board[i][c + k] = board[i - 1][c + k];
            }
            board[r - k + 1][c + k] = tmp;
        }
    }
}