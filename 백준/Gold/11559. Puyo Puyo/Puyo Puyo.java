import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static char[][] board = new char[12][6];
    private static boolean[][] visit;

    private static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new char[12][6];
        for (int i = 0; i < 12; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int answer;
        for (answer = 0; answer <= 20; answer++) {
            // 지워야 하는 위치들을 찾음
            List<Position> removes = new ArrayList<>();
            visit = new boolean[12][6];
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (board[i][j] == '.') continue;
                    if (find2Remove(i, j) >= 4) {
                        removes.add(new Position(i, j));
                    }
                }
            }

            // 지울 곳이 없으면 몇연쇄인지 출력하고 종료
            if (removes.isEmpty()) {
                System.out.println(answer);
                return;
            }

            // 지워야 할 뿌요들을 지움
            visit = new boolean[12][6];
            for (Position cur : removes) {
                remove(cur.x, cur.y);
            }

            // 뿌요들을 전부 아래로 내림
            for (int j = 0; j < 6; j++) {
                Queue<Character> que = new LinkedList<>();
                for (int i = 11; i >= 0; i--) {
                    char ch = board[i][j];
                    if (ch != '.') que.offer(ch);
                }
                int idx = 11;
                while (!que.isEmpty()) {
                    board[idx--][j] = que.poll();
                }
                while (idx >= 0) {
                    board[idx--][j] = '.';
                }
            }
        }
    }

    private static void remove(int x, int y) {
        visit[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6) continue;
            if (visit[nx][ny] || board[nx][ny] != board[x][y]) continue;
            remove(nx, ny);
        }
        board[x][y] = '.';
    }

    private static int find2Remove(int x, int y) {
        visit[x][y] = true;
        int sum = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6) continue;
            if (visit[nx][ny] || board[nx][ny] != board[x][y]) continue;
            sum += find2Remove(nx, ny);
        }
        return sum;
    }
}