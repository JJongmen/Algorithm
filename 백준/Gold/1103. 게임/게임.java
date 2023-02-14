import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static final int HOLE = -1;
    static int N, M;
    static int[][] board = new int[50][50];
    static boolean[][] visit = new boolean[50][50];
    static int[][] dist = new int[50][50];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                if (row.charAt(j) == 'H') {
                    board[i][j] = HOLE;
                } else {
                    board[i][j] = row.charAt(j) - '0';
                }
            }
        }

        visit[0][0] = true;
        dfs(0, 0, 1);
        System.out.println(result);
    }

    private static void dfs(int x, int y, int cnt) {
        int X = board[x][y];
        result = Math.max(result, cnt);
        for (int i = 0; i < 4; i++) {
            int nx = x + X * dx[i];
            int ny = y + X * dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if (board[nx][ny] == HOLE) continue;
            if (cnt < dist[nx][ny]) continue;
            if (visit[nx][ny]) {
                System.out.println(-1);
                System.exit(0);
            }
            visit[nx][ny] = true;
            dist[nx][ny] = cnt + 1;
            dfs(nx, ny, cnt + 1);
            visit[nx][ny] = false;
        }
    }
}