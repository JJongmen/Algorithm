import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N, M;
    static int[][] board;
    static boolean[][] visit;
    static List<Cheese> cheeses = new ArrayList<>();
    static int totalCnt = 0;

    static class Cheese {
        int x, y;
        boolean isLive;

        public Cheese(int x, int y) {
            this.x = x;
            this.y = y;
            isLive = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    cheeses.add(new Cheese(i, j));
                    totalCnt++;
                }
            }
        }

        int time = 1;
        while (true) {
            visit = new boolean[N][M];
            dfs(0, 0);

            for (Cheese cheese : cheeses) {
                if (!cheese.isLive) continue;
                int cnt = 0;
                for (int i = 0; i < 4; i++) {
                    int nx = cheese.x + dx[i];
                    int ny = cheese.y + dy[i];
                    if (board[nx][ny] == 2) cnt++;
                }
                if (cnt >= 2) {
                    cheese.isLive = false;
                    totalCnt--;
                }
            }

            if (totalCnt == 0) break;
            for (Cheese cheese : cheeses) {
                if (cheese.isLive) continue;
                board[cheese.x][cheese.y] = 2;
            }
            time++;
        }

        System.out.println(time);
    }

    private static void dfs(int sx, int sy) {
        board[sx][sy] = 2;
        visit[sx][sy] = true;
        for (int i = 0; i < 4; i++) {
            int nx = sx + dx[i];
            int ny = sy + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if (board[nx][ny] == 1 || visit[nx][ny]) continue;
            dfs(nx, ny);
        }
    }
}