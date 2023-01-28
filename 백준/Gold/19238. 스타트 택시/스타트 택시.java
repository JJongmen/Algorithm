import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, cFuel;
    static int[][] board = new int[21][21]; // 0: 빈칸, -1: 벽, 1~N: 손님
    static Position[] destination = new Position[401];
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int tx, ty;  // 택시의 위치

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
        cFuel = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) board[i][j] = -1;
            }
        }

        st = new StringTokenizer(br.readLine());
        tx = Integer.parseInt(st.nextToken());
        ty = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            board[sx][sy] = i;

            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());
            destination[i] = new Position(ex, ey);
        }

        while (M > 0 && cFuel > 0) {
            if (!findPassenger()) {
                System.out.println(-1);
                return;
            }
            if (!moveDestination()) {
                System.out.println(-1);
                return;
            }
            M--;
        }
        System.out.println(cFuel);
    }

    private static boolean moveDestination() {
        Position dest = destination[board[tx][ty]];
        board[tx][ty] = 0;
        int[][] dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], 1, N + 1, -1);
        }
        dist[tx][ty] = 0;
        Queue<Position> que = new LinkedList<>();
        que.offer(new Position(tx, ty));
        while (!que.isEmpty()) {
            Position cur = que.poll();
            if (dist[cur.x][cur.y] >= cFuel) return false;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
                if (board[nx][ny] == -1 || dist[nx][ny] != -1) continue;
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
                if (nx == dest.x && ny == dest.y) {
                    tx = nx;
                    ty = ny;
                    cFuel += dist[nx][ny];
                    return true;
                }
                que.offer(new Position(nx, ny));
            }
        }
        return false;
    }

    private static boolean findPassenger() {
        if (board[tx][ty] > 0) {
            return true;
        }
        int[][] dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], 1, N + 1, -1);
        }
        dist[tx][ty] = 0;
        Queue<Position> que = new LinkedList<>();
        que.offer(new Position(tx, ty));
        PriorityQueue<Position> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.x == o2.x) {
                return o1.y - o2.y;
            }
            return o1.x - o2.x;
        });
        int minDist = 1000000;
        while (!que.isEmpty()) {
            Position cur = que.poll();
            if (dist[cur.x][cur.y] >= minDist || dist[cur.x][cur.y] >= cFuel) break;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
                if (board[nx][ny] == -1 || dist[nx][ny] != -1) continue;
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
                if (board[nx][ny] > 0) {
                    if (pq.isEmpty()) {
                        minDist = dist[nx][ny];
                    }
                    pq.offer(new Position(nx, ny));
                }
                que.offer(new Position(nx, ny));
            }
        }
        if (pq.isEmpty()) {
            return false;
        }
        Position passenger = pq.poll();
        tx = passenger.x;
        ty = passenger.y;
        cFuel -= dist[tx][ty];
        return true;
    }
}