import java.io.*;
import java.util.StringTokenizer;

public class Main {
    // 동, 서, 남, 북 = (1, 2, 3, 4)
    private static final int EAST = 1;
    private static final int WEST = 2;
    private static final int NORTH = 3;
    private static final int SOUTH = 4;
    private static int[] dx = {0, 0, 0, -1, 1};
    private static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][M];
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
        int[][] dice = new int[4][4];
        st = new StringTokenizer(br.readLine());
        while (K-- > 0) {
            int d = Integer.parseInt(st.nextToken());
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
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
            if (board[nx][ny] == 0) {
                board[nx][ny] = dice[3][1];
            } else {
                dice[3][1] = dice[1][3] = board[nx][ny];
                board[nx][ny] = 0;
            }
            bw.write(dice[1][1] + "\n");
            x = nx;
            y = ny;
        }
        bw.flush();
        br.close();
        bw.close();
    }
}