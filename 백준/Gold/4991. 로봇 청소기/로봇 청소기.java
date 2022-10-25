import java.io.*;
import java.util.*;

public class Main {
    static int w, h;
    static char[][] room;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static List<Position> dirtyPositions;
    static int cleanerIdx;
    static int[][] dist;
    static boolean[] isUsed;
    static int result;

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 청소기와 더러운 칸 사이의 거리 / 더러운 칸과 더러운 칸 사이의 거리를 구한다.
    static boolean bfs(Position start, int order) {
        int findCnt = 1;
        int[][] visit = new int[h][w];
        Queue<Position> que = new LinkedList<>();
        visit[start.x][start.y] = 1;
        que.offer(start);
        while (!que.isEmpty()) {
            Position cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                if (room[nx][ny] == 'x' || visit[nx][ny] > 0) continue;
                visit[nx][ny] = visit[cur.x][cur.y] + 1;
                que.offer(new Position(nx, ny));
                if (room[nx][ny] == '*') {
                    findCnt++;
                    for (int k = 0; k < dirtyPositions.size(); k++) {
                        Position pos = dirtyPositions.get(k);
                        if (pos.x != nx || pos.y != ny) continue;
                        dist[order][k] = visit[nx][ny] - 1;
                        break;
                    }
                }
            }
        }
        return findCnt == dirtyPositions.size();
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) {
                break;
            }
            room = new char[h][w];
            dirtyPositions = new ArrayList<>();
            for (int i = 0; i < h; i++) {
                room[i] = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    if (room[i][j] == 'o') {
                        room[i][j] = '*';
                        cleanerIdx = dirtyPositions.size();
                        dirtyPositions.add(new Position(i, j));
                    } else if (room[i][j] == '*') {
                        dirtyPositions.add(new Position(i, j));
                    }
                }
            }

            dist = new int[15][15];
            isUsed = new boolean[15];
            result = 10000000;
            if (!findAllDist()) {
                bw.write(-1 + "\n");
                continue;
            }
            isUsed[cleanerIdx] = true;
            for (int i = 0; i < dirtyPositions.size(); i++) {
                calculateMinDist(1, i, dist[cleanerIdx][i]);
            }
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void calculateMinDist(int cur, int order, int totalDist) {
        if (cur == dirtyPositions.size()) {
            result = Math.min(result, totalDist);
            return;
        }
        for (int i = 0; i < dirtyPositions.size(); i++) {
            if (isUsed[i]) continue;
            isUsed[i] = true;
            calculateMinDist(cur + 1, i, totalDist + dist[order][i]);
            isUsed[i] = false;
        }
    }

    // 모든 더러운 칸에 방문할 수 있다면 true
    static boolean findAllDist() {
        for (int i = 0; i < dirtyPositions.size(); i++) {
            if (!bfs(dirtyPositions.get(i), i)) return false;
        }
        return true;
    }
}