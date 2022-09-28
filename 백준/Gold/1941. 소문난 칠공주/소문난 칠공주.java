import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static String[] classroom = new String[5];
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int answer = 0;

    private static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void bt(int cur, int cnt, int state) {
        if (cnt == 7) {
            int[][] board = new int[5][5];
            int dasom = 0;
            Position start = null;
            for (int i = 0; i < 25; i++) {
                if ((state & (1 << i)) != 0) {
                    int x = i / 5;
                    int y = i % 5;
                    if (classroom[x].charAt(y) == 'S') dasom++;
                    board[x][y] = 1;
                    if (start == null) start = new Position(x, y);
                }
            }
            if (dasom < 4) return;
            int count = 0;
            Queue<Position> que = new LinkedList<>();
            board[start.x][start.y] = 0;
            que.offer(start);
            while (!que.isEmpty()) {
                count++;
                Position curPos = que.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = curPos.x + dx[i];
                    int ny = curPos.y + dy[i];
                    if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                    if (board[nx][ny] != 1) continue;
                    board[nx][ny] = 0;
                    que.offer(new Position(nx, ny));
                }
            }
            if (count == 7) answer++;
            return;
        }
        if (cur == 25) return;
        bt(cur + 1, cnt, state);
        bt(cur + 1, cnt + 1, state | (1 << cur));
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            classroom[i] = scan.nextLine();
        }
        bt(0, 0, 0);
        System.out.println(answer);
    }
}