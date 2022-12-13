import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 10000;
    static int N, M;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

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
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int[][] board = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                board[i][j] = line.charAt(j - 1) - '0';
            }
        }

        int[][] dist = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], 1, M + 1, INF);
        }
        Queue<Position> que = new LinkedList<>();
        que.offer(new Position(1, 1));
        dist[1][1] = 0;
        while (!que.isEmpty()) {
            Position cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 1 || nx > N || ny < 1 || ny > M) continue;
                int nxtDist = dist[cur.x][cur.y] + board[nx][ny];
                if (dist[nx][ny] <= nxtDist) continue;
                dist[nx][ny] = nxtDist;
                que.offer(new Position(nx, ny));
            }
        }
        System.out.println(dist[N][M]);
    }
}