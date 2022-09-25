import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visit;
    static int[][] board;
    static int n;
    static int m;

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visit = new boolean[n][m];
        List<Position> colorings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    colorings.add(new Position(i, j));
                }
            }
        }

        int paintingCnt = 0;
        int maxArea = 0;
        for (Position coloring : colorings) {
            if (!visit[coloring.x][coloring.y]) {
                paintingCnt++;
                maxArea = Math.max(maxArea, bfs(coloring.x, coloring.y));
            }
        }
        bw.write(paintingCnt + "\n");
        bw.write(maxArea + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int startX, int startY) {
        Queue<Position> que = new LinkedList<>();
        visit[startX][startY] = true;
        que.offer(new Position(startX, startY));
        int cnt = 0;
        while (!que.isEmpty()) {
            Position curr = que.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nextX = curr.x + dx[i];
                int nextY = curr.y + dy[i];
                if (nextX < 0 | nextX >= n | nextY < 0 | nextY >= m) continue;
                if (visit[nextX][nextY] | board[nextX][nextY] == 0) continue;
                visit[nextX][nextY] = true;
                que.offer(new Position(nextX, nextY));
            }
        }
        return cnt;
    }
}