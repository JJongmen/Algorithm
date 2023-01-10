import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 10000;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N, M;
    static int[][] board;
    static boolean[][] visit;
    static int[][] dist;
    static int[] parents;

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Bridge {
        int x, y;
        int dist;

        public Bridge(int x, int y, int dist) {
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
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        visit = new boolean[N][M];
        int islandCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0 || visit[i][j]) continue;
                bfs(i, j, ++islandCnt);
            }
        }

        dist = new int[islandCnt + 1][islandCnt + 1];
        for (int i = 1; i <= islandCnt; i++) {
            Arrays.fill(dist[i], 1, islandCnt + 1, INF);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) continue;
                checkDist(i, j);
            }
        }

        List<Bridge> bridges = new ArrayList<>();
        for (int i = 1; i < islandCnt; i++) {
            for (int j = i + 1; j <= islandCnt; j++) {
                if (dist[i][j] == INF) continue;
                bridges.add(new Bridge(i, j, dist[i][j]));
            }
        }

        parents = new int[islandCnt + 1];
        for (int i = 1; i <= islandCnt; i++) {
            parents[i] = i;
        }

        bridges.sort(Comparator.comparingInt(o -> o.dist));
        int result = 0;
        int cnt = 0;
        for (Bridge bridge : bridges) {
            if (!is_diff_group(bridge.x, bridge.y)) continue;
            result += bridge.dist;
            if (++cnt == islandCnt - 1) break;
        }
        System.out.println(cnt == islandCnt - 1 ? result : -1);
    }

    private static boolean is_diff_group(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parents[y] = x;
            return true;
        }
        return false;
    }

    private static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static void checkDist(int sx, int sy) {
        int sn = board[sx][sy];
        for (int i = 0; i < 4; i++) {
            int nx = sx + dx[i];
            int ny = sy + dy[i];
            int d = 0;
            while (0 <= nx && nx < N && 0 <= ny && ny < M && board[nx][ny] != sn) {
                if (board[nx][ny] != 0) {
                    if (d >= 2) {
                        int en = board[nx][ny];
                        dist[sn][en] = Math.min(dist[sn][en], d);
                    }
                    break;
                }
                nx += dx[i];
                ny += dy[i];
                d++;
            }

        }
    }

    private static void bfs(int sx, int sy, int num) {
        Queue<Position> que = new LinkedList<>();
        que.offer(new Position(sx, sy));
        visit[sx][sy] = true;
        board[sx][sy] = num;
        while (!que.isEmpty()) {
            Position cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (visit[nx][ny] || board[nx][ny] == 0) continue;
                que.offer(new Position(nx, ny));
                visit[nx][ny] = true;
                board[nx][ny] = num;
            }
        }
    }
}