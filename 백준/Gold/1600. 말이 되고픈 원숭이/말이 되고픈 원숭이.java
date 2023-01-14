import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[] hx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] hy = {1, 2, 2, 1, -1, -2, -2, -1};
    static int K, W, H;
    static int[][] board;
    static int[][][] visit;

    static class Position {
        int x, y;
        int cnt, horseCnt;

        public Position(int x, int y, int cnt, int horseCnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.horseCnt = horseCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit = new int[H][W][2];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                visit[i][j][0] = 50000;
                visit[i][j][1] = 50;
            }
        }

        Queue<Position> que = new LinkedList<>();
        que.offer(new Position(0, 0, 0, 0));
        visit[0][0][0] = visit[0][0][1] = 0;
        while (!que.isEmpty()) {
            Position cur = que.poll();
            if (cur.x == H - 1 && cur.y == W - 1) break;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
                if (board[nx][ny] == 1) continue;
                if (visit[nx][ny][0] <= cur.cnt + 1 && visit[nx][ny][1] <= cur.horseCnt) continue;
                que.offer(new Position(nx, ny, cur.cnt + 1, cur.horseCnt));
                visit[nx][ny][0] = cur.cnt + 1;
                visit[nx][ny][1] = cur.horseCnt;
            }
            if (cur.horseCnt == K) continue;
            for (int i = 0; i < 8; i++) {
                int nx = cur.x + hx[i];
                int ny = cur.y + hy[i];
                if (nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
                if (board[nx][ny] == 1) continue;
                if (visit[nx][ny][0] <= cur.cnt + 1 && visit[nx][ny][1] <= cur.horseCnt + 1) continue;
                que.offer(new Position(nx, ny, cur.cnt + 1, cur.horseCnt + 1));
                visit[nx][ny][0] = cur.cnt + 1;
                visit[nx][ny][1] = cur.horseCnt + 1;
            }
        }
        System.out.println(visit[H - 1][W - 1][0] == 50000 ? -1 : visit[H - 1][W - 1][0]);
    }
}