import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 10000;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N;
    static int[][] board;

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int order = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            board = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Problem ")
                    .append(order++)
                    .append(": ")
                    .append(bfs())
                    .append("\n");
            bw.write(sb.toString());
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs() {
        int[][] d = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(d[i], INF);
        }

        Queue<Position> que = new LinkedList<>();
        que.offer(new Position(0, 0));
        d[0][0] = board[0][0];
        while (!que.isEmpty()) {
            Position cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                int nd = d[cur.x][cur.y] + board[nx][ny];
                if (d[nx][ny] <= nd) continue;
                que.offer(new Position(nx, ny));
                d[nx][ny] = nd;
            }
        }
        return d[N - 1][N - 1];
    }
}