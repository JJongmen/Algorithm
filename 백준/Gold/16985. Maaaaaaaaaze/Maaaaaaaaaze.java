import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int IMPOSSIBLE = 10000;
    static int[] dx = {0, 0, 1, -1, 0, 0};
    static int[] dy = {1, -1, 0, 0, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static int[] startX = {0, 0, 4, 4};
    static int[] startY = {0, 4, 0, 4};
    static int[][][] maze = new int[5][5][5];
    static int[][][] rotatedMaze = new int[5][5][5];
    static int[][][] visit;
    static int[] order = new int[5];
    static boolean[] selected = new boolean[5];
    static int result = IMPOSSIBLE;

    static class Position {
        int x;
        int y;
        int z;

        public Position(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    // idx 번째 판을 시계 방향으로 90도 회전시킨다
    static int[][] rotate(int idx) {
        int[][] tmpBoard = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tmpBoard[i][j] = rotatedMaze[idx][4 - j][i];
            }
        }
        return tmpBoard;
    }

    static void bfs(int sx, int sy, int sz) {
        int fx = 4 - sx;
        int fy = 4 - sy;
        int fz = 4 - sz;
        Queue<Position> que = new LinkedList<>();
        visit[sx][sy][sz] = 1;
        que.offer(new Position(sx, sy, sz));
        while (!que.isEmpty()) {
            Position cur = que.poll();
            // 현재 방문한 거리가 최단거리보다 크면 더 탐색할 이유가 없음
            if (visit[cur.x][cur.y][cur.z] - 1 > result) return;
            for (int i = 0; i < 6; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nz = cur.z + dz[i];
                // 미로 밖의 좌표인 경우 건너뜀
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || nz < 0 || nz >= 5) continue;
                // 참가자가 들어갈 수 없는 칸이거나 이미 방문했던 칸이라면 건너뜀
                if (rotatedMaze[nx][ny][nz] == 0 || visit[nx][ny][nz] != 0) continue;
                visit[nx][ny][nz] = visit[cur.x][cur.y][cur.z] + 1;
                if (nx == fx && ny == fy && nz == fz) {
                    result = Math.min(result, visit[nx][ny][nz] - 1);
                    return;
                }
                que.offer(new Position(nx, ny, nz));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    maze[i][j][k] = Integer.parseInt(st.nextToken());
                    rotatedMaze[i][j][k] = maze[i][j][k];
                }
            }
        }

        bt(0);

        System.out.println(result == IMPOSSIBLE ? -1 : result);
    }

    private static void bt(int cur) {
        if (cur == 5) {
            for (int i = 0; i < 5; i++) {
                rotatedMaze[i] = maze[order[i]].clone();
            }
            for (int tmp = 0; tmp < 1024; tmp++) {
                int brute = tmp;
                for (int i = 0; i < 5; i++) {
                    int dir = brute % 4;
                    while (dir-- > 0) {
                        rotatedMaze[i] = rotate(i);
                    }
                    brute /= 4;
                }
                for (int i = 0; i < 4; i++) {
                    int sx = startX[i];
                    int sy = startY[i];
                    if (rotatedMaze[sx][sy][0] == 1 && rotatedMaze[4 - sx][4 - sy][4] == 1) {
                        visit = new int[5][5][5];
                        bfs(sx, sy, 0);
                    }
                }
            }
            return;
        }
        for (int i = 0; i < 5; i++) {
            if (selected[i]) continue;
            selected[i] = true;
            order[cur] = i;
            bt(cur + 1);
            selected[i] = false;
        }
    }
}