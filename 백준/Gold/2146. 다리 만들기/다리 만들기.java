import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N;
    static int[][] map = new int[100][100];
    static boolean[][] visit = new boolean[100][100];
    static int cntOfKind = 0;
    static List<Set<Position>> coasts = new ArrayList<>();
    static int result = 1000;

    static {
        coasts.add(new HashSet<>());
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0 && !visit[i][j]) {
                    cntOfKind++;
                    coasts.add(new HashSet<>());
                    markingIsland(i, j);
                }
            }
        }

        for (int kind = 1; kind <= cntOfKind; kind++) {
            searchIslandWithout(kind);
        }

        System.out.println(result);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void markingIsland(int sx, int sy) {
        Queue<Position> que = new LinkedList<>();
        que.offer(new Position(sx, sy));
        visit[sx][sy] = true;
        map[sx][sy] = cntOfKind;
        while (!que.isEmpty()) {
            Position cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visit[nx][ny]) continue;
                if (map[nx][ny] == 0) {
                    coasts.get(cntOfKind).add(cur);
                    continue;
                }
                que.offer(new Position(nx, ny));
                visit[nx][ny] = true;
                map[nx][ny] = cntOfKind;
            }
        }
    }

    private static void searchIslandWithout(int kind) {
        Queue<Position> que = new LinkedList<>(coasts.get(kind));
        int[][] dist = new int[N][N];
        while (!que.isEmpty()) {
            Position cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (map[nx][ny] == kind || dist[nx][ny] > 0) continue;
                if (map[nx][ny] > 0) {
                    result = Math.min(result, dist[cur.x][cur.y]);
                    return;
                }
                que.offer(new Position(nx, ny));
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
            }
        }
    }
}