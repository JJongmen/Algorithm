import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

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
        int T = Integer.parseInt(br.readLine());
        cycle : while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            char[][] board = new char[h][w];
            int[][] person = new int[h][w];
            int[][] fire = new int[h][w];
            Queue<Position> que = new LinkedList<>();
            Position start = null;
            for (int i = 0; i < h; i++) {
                String row = br.readLine();
                Arrays.fill(person[i], -1);
                Arrays.fill(fire[i], -1);
                for (int j = 0; j < w; j++) {
                    board[i][j] = row.charAt(j);
                    if (board[i][j] == '*') {
                        fire[i][j] = 0;
                        que.offer(new Position(i, j));
                    } else if (board[i][j] == '@') {
                        person[i][j] = 0;
                        start = new Position(i, j);
                    }
                }
            }

            while (!que.isEmpty()) {
                Position cur = que.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (nx < 0 || nx >= h || ny < 0 | ny >= w) continue;
                    if (board[nx][ny] == '#' || fire[nx][ny] >= 0) continue;
                    fire[nx][ny] = fire[cur.x][cur.y] + 1;
                    que.offer(new Position(nx, ny));
                }
            }

            que.offer(start);
            while (!que.isEmpty()) {
                Position cur = que.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (nx < 0 || nx >= h || ny < 0 | ny >= w) {
                        bw.write(person[cur.x][cur.y] + 1 + "\n");
                        continue cycle;
                    }
                    if (board[nx][ny] == '#' || person[nx][ny] >= 0) continue;
                    if (fire[nx][ny] != -1 && fire[nx][ny] <= person[cur.x][cur.y] + 1) continue;
                    person[nx][ny] = person[cur.x][cur.y] + 1;
                    que.offer(new Position(nx, ny));
                }
            }
            bw.write("IMPOSSIBLE\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}