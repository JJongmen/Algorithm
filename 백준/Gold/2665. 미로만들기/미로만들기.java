import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static final int INF = 500;
    static int N;
    static char[][] board;
    static int[][] visit = new int[50][50];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Position {
        int x, y;
        int changeCnt;

        public Position(int x, int y, int changeCnt) {
            this.x = x;
            this.y = y;
            this.changeCnt = changeCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(visit[i], INF);
        }

        Queue<Position> que = new LinkedList<>();
        que.offer(new Position(0, 0, 0));
        visit[0][0] = 0;
        while (!que.isEmpty()) {
            Position cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                int nxtChangeCnt = cur.changeCnt;
                if (board[nx][ny] == '0') {
                    nxtChangeCnt++;
                }
                if (visit[nx][ny] <= nxtChangeCnt) continue;
                visit[nx][ny] = nxtChangeCnt;
                que.offer(new Position(nx, ny, nxtChangeCnt));
            }
        }
        System.out.println(visit[N - 1][N - 1]);
    }
}