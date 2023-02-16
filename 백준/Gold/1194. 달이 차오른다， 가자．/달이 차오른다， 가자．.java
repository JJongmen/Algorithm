import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int NON_VISIT = -1;
    static int N, M;
    static char[][] board = new char[50][50];
    static int[][][] dist = new int[50][50][1 << 6];
    static int sx, sy;  // 출발 위치
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Position {
        int x, y;
        int key;

        public Position(int x, int y, int key) {
            this.x = x;
            this.y = y;
            this.key = key;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    ", key=" + key +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = row.charAt(j);
                if (board[i][j] == '0') {
                    sx = i;
                    sy = j;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dist[i][j], NON_VISIT);
            }
        }

        System.out.println(escapeMaze());
    }

    private static int escapeMaze() {
        Queue<Position> que = new LinkedList<>();
        que.offer(new Position(sx, sy, 0));
        dist[sx][sy][0] = 0;
        while (!que.isEmpty()) {
            Position cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                // 미로 밖으로 이동하려는 경우 방문하지 않는다.
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                // 이동하려는 곳이 벽이거나 이미 방문한 경우 방문하지 않는다.
                if (board[nx][ny] == '#' || dist[nx][ny][cur.key] != NON_VISIT) continue;
                // 이동하려는 곳이 문인 경우 대응하는 열쇠가 없으면 방문하지 않는다.
                if ('A' <= board[nx][ny] && board[nx][ny] <= 'F' && (cur.key & (1 << ('F' - board[nx][ny]))) == 0) continue;
                int nxtKey = cur.key;
                if ('a' <= board[nx][ny] && board[nx][ny] <= 'f') {
                    nxtKey |= 1 << ('F' - board[nx][ny]);
                }
                dist[nx][ny][nxtKey] = dist[cur.x][cur.y][cur.key] + 1;
                // 출구로 이동하여 탈출한다.
                if (board[nx][ny] == '1') {
                    return dist[nx][ny][nxtKey];
                }
                que.offer(new Position(nx, ny, nxtKey));
            }
        }
        // 미로를 탈출하지 못한 경우 -1을 반환한다.
        return -1;
    }
}