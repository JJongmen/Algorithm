import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N;
    static int[][] board;
    static int[][] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        d = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(d[i], -1);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (d[i][j] != -1) continue;
                dfs(i, j, 1);
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result = Math.max(result, d[i][j]);
            }
        }
        System.out.println(result);
    }

    private static int dfs(int x, int y, int dist) {
        if (d[x][y] != -1) {
            return d[x][y];
        }
        int max = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (board[nx][ny] <= board[x][y]) continue;
            max = Math.max(max, dfs(nx, ny, dist + 1));
        }
        return d[x][y] = max + 1;
    }
}