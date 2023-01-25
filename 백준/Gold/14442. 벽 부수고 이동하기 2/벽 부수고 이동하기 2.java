import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 1000005;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N, M, K;
    static char[][] board;

    static class Position {
        int x, y;
        int breakCnt;

        public Position(int x, int y, int breakCnt) {
            this.x = x;
            this.y = y;
            this.breakCnt = breakCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new char[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int[][][] dist = new int[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dist[i][j], INF);
            }
        }

        Queue<Position> que = new LinkedList<>();
        que.offer(new Position(0, 0, 0));
        dist[0][0][0] = 1;
        while (!que.isEmpty()) {
            Position cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                int nxtBreakCnt = cur.breakCnt;
                if (board[nx][ny] == '1') {
                    nxtBreakCnt++;
                }
                if (nxtBreakCnt > K) continue;
                int nxtDist = dist[cur.x][cur.y][cur.breakCnt] + 1;
                if (dist[nx][ny][nxtBreakCnt] > nxtDist) {
                    que.offer(new Position(nx, ny, nxtBreakCnt));
                    dist[nx][ny][nxtBreakCnt] = nxtDist;
                }
            }
        }

        int result = INF;
        for (int d : dist[N - 1][M - 1]) {
            result = Math.min(result, d);
        }
        System.out.println(result == INF ? -1 : result);
    }
}