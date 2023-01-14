import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, -1, 0};
    static int[] dy = {-1, 0, 1};
    static int N, M, D;
    static int[][] board, tmpBoard;
    static int[] archer = new int[3];
    static int result;
    static int archerIdx = 0;

    static class Position {
        int x, y;
        int dist;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        setArcher(0);
        System.out.println(result);
    }

    private static void setArcher(int pos) {
        if (pos == 3) {
            tmpBoard = new int[N][M];
            for (int i = 0; i < N; i++) {
                tmpBoard[i] = board[i].clone();
            }

            result = Math.max(result, simulation());
            return;
        }
        for (int i = pos == 0 ? 0 : archer[pos - 1] + 1; i < M; i++) {
            archer[pos] = i;
            setArcher(pos + 1);
        }
    }

    private static int simulation() {
        int cnt = 0;
        archerIdx = N;
        while (true) {
            cnt += attackEnemy();
            moveEnemy();
            if (!hasEnemy()) break;
        }
        return cnt;
    }

    private static int attackEnemy() {
        int cnt = 0;
        Position[] willDie = detectEnemy();
        for (int i = 0; i < 3; i++) {
            if (willDie[i] == null) continue;
            int x = willDie[i].x;
            int y = willDie[i].y;
            if (tmpBoard[x][y] == 0) continue;
            tmpBoard[x][y] = 0;
            cnt++;
        }
        return cnt;
    }

    private static Position[] detectEnemy() {
        Position[] willDie = new Position[3];
        for (int i = 0; i < 3; i++) {
            Queue<Position> que = new LinkedList<>();
            que.offer(new Position(archerIdx, archer[i], 0));
            boolean[][] visit = new boolean[archerIdx][M];
            while (!que.isEmpty()) {
                Position cur = que.poll();
                if (cur.dist == D) break;
                for (int j = 0; j < 3; j++) {
                    int nx = cur.x + dx[j];
                    int ny = cur.y + dy[j];
                    if (nx < 0 || nx >= archerIdx || ny < 0 || ny >= M) continue;
                    if (visit[nx][ny]) continue;
                    if (tmpBoard[nx][ny] == 1) {
                        willDie[i] = new Position(nx, ny);
                        break;
                    }
                    que.offer(new Position(nx, ny, cur.dist + 1));
                    visit[nx][ny] = true;
                }
                if (willDie[i] != null) break;
            }
        }
        return willDie;
    }

    private static void moveEnemy() {
        archerIdx--;
    }

    private static boolean hasEnemy() {
        for (int i = 0; i < archerIdx; i++) {
            for (int j = 0; j < M; j++) {
                if (tmpBoard[i][j] == 1) return true;
            }
        }
        return false;
    }
}