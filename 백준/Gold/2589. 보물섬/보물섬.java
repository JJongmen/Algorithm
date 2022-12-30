import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N, M;
    static char[][] board;

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'W') continue;
                result = Math.max(result, getMaxDist(i, j));
            }
        }
        System.out.println(result);
    }

    private static int getMaxDist(int sx, int sy) {
        int[][] d = new int[N][M];
        Queue<Position> que = new LinkedList<>();
        que.offer(new Position(sx, sy));
        d[sx][sy] = 1;
        int max = 0;
        while (!que.isEmpty()) {
            Position cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (board[nx][ny] == 'W' || d[nx][ny] != 0) continue;
                que.offer(new Position(nx, ny));
                max = d[nx][ny] = d[cur.x][cur.y] + 1;
            }
        }
        return max - 1;
    }
}