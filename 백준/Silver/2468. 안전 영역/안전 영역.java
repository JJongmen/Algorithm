import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] board = new int[100][100];
    static boolean[][] visit = new boolean[100][100];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        for (int h = 0; h <= 100; h++) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                Arrays.fill(visit[i], 0, N, false);
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visit[i][j] || board[i][j] <= h) continue;
                    dfs(i, j, h);
                    cnt++;
                }
            }
            result = Math.max(result, cnt);
        }
        System.out.println(result);
    }

    private static void dfs(int x, int y, int height) {
        visit[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (visit[nx][ny] || board[nx][ny] <= height) continue;
            dfs(nx, ny, height);
        }
    }
}