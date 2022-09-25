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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        String[] board = new String[R];
        int[][] person = new int[R][C];
        int[][] fire = new int[R][C];
        Queue<Position> que = new LinkedList<>();
        Position start = null;
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine();
            Arrays.fill(person[i], -1);
            Arrays.fill(fire[i], -1);
            for (int j = 0; j < C; j++) {
                char ch = board[i].charAt(j);
                if (ch == 'F') {
                    fire[i][j] = 0;
                    que.offer(new Position(i, j));
                } else if (ch == 'J') {
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
                if (nx < 0 || nx >= R || ny < 0 | ny >= C) continue;
                if (board[nx].charAt(ny) == '#' || fire[nx][ny] >= 0) continue;
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
                if (nx < 0 || nx >= R || ny < 0 | ny >= C) {
                    System.out.println(person[cur.x][cur.y] + 1);
                    return;
                }
                if (board[nx].charAt(ny) == '#' || person[nx][ny] >= 0) continue;
                if (fire[nx][ny] != -1 && fire[nx][ny] <= person[cur.x][cur.y] + 1) continue;
                person[nx][ny] = person[cur.x][cur.y] + 1;
                que.offer(new Position(nx, ny));
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}