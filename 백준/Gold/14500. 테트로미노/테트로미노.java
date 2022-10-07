import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;
    private static int[][] board = new int[505][505];
    private static boolean[][] visit = new boolean[505][505];
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int[][] exDx = {{0, 0, 0, 1}, {0, 0, 0, -1}, {0, 1, 2, 1}, {0, 1, 2, 1}};
    private static int[][] exDy = {{0, 1, 2, 1}, {0, 1, 2, 1}, {0, 0, 0, -1}, {0, 0, 0, 1}};
    private static int max = 0;

    // ㅜ 모양을 제외한 나머지 모양 탐색
    private static void dfs(int cur, int sum, int sx, int sy) {
        if (cur == 4) {
            max = Math.max(max, sum);
            return;
        }
        visit[sx][sy] = true;
        for (int i = 0; i < 4; i++) {
            int nx = sx + dx[i];
            int ny = sy + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if (visit[nx][ny]) continue;
            dfs(cur + 1, sum + board[nx][ny], nx, ny);
        }
        visit[sx][sy] = false;
    }

    // ㅜ 모양 탐색
    private static void exDfs(int sx, int sy) {
        for (int i = 0; i < 4; i++) {
            int sum = 0;
            for (int j = 0; j < 4; j++) {
                int nx = sx + exDx[i][j];
                int ny = sy + exDy[i][j];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
                sum += board[nx][ny];
            }
            max = Math.max(max, sum);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(0, 0, i, j);
                exDfs(i, j);
            }
        }
        System.out.println(max);
    }
}