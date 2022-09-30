import java.io.*;
import java.util.StringTokenizer;

public class Main {
    // 동, 남, 서, 북 = (0, 1, 2, 3)
    private static final int EAST = 0;
    private static final int SOUTH = 1;
    private static final int WEST = 2;
    private static final int NORTH = 3;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static int N;
    private static int M;
    private static int[][] board;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        /**
         *   0 북 0  0
         *  서 위 동 아래
         *   0 남 0  0
         *  0 아래 0 0
         */
        int[][] dice = initDice();
        int x = 0;
        int y = 0;
        int d = 0;
        int sum = 0;
        while (K-- > 0) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                d = (d + 2) % 4;
                nx = x + dx[d];
                ny = y + dy[d];
            }
            moveDice(dice, d);

            visit = new boolean[N][M];
            visit[nx][ny] = true;
            int cnt = dfs(nx, ny, board[nx][ny]);
            sum += cnt * board[nx][ny];

            int A = dice[3][1];
            int B = board[nx][ny];
            if (A > B) {
                d = (d + 1) % 4;
            } else if (A < B) {
                d = (d + 3) % 4;
            }

            x = nx;
            y = ny;
        }
        System.out.println(sum);
    }

    private static int dfs(int x, int y, int num) {
        int sum = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if (visit[nx][ny] || board[nx][ny] != num) continue;
            visit[nx][ny] = true;
            sum += dfs(nx, ny, num);
        }
        return sum;
    }

    private static int[][] initDice() {
        int[][] dice = new int[4][4];
        dice[1][1] = 1;
        dice[0][1] = 2;
        dice[1][2] = 3;
        dice[1][0] = 4;
        dice[2][1] = 5;
        dice[3][1] = 6;
        dice[1][3] = 6;
        return dice;
    }

    private static void moveDice(int[][] dice, int d) {
        if (d == EAST) {
            int tmp = dice[1][3];
            for (int i = 3; i > 0; i--) {
                dice[1][i] = dice[1][i - 1];
            }
            dice[1][0] = tmp;
            dice[3][1] = dice[1][3];
        } else if (d == WEST) {
            int tmp = dice[1][0];
            for (int i = 0; i < 3; i++) {
                dice[1][i] = dice[1][i + 1];
            }
            dice[1][3] = tmp;
            dice[3][1] = dice[1][3];
        } else if (d == NORTH) {
            int tmp = dice[0][1];
            for (int i = 0; i < 3; i++) {
                dice[i][1] = dice[i + 1][1];
            }
            dice[3][1] = tmp;
            dice[1][3] = dice[3][1];
        } else if (d == SOUTH) {
            int tmp = dice[3][1];
            for (int i = 3; i > 0; i--) {
                dice[i][1] = dice[i - 1][1];
            }
            dice[0][1] = tmp;
            dice[1][3] = dice[3][1];
        }
    }
}