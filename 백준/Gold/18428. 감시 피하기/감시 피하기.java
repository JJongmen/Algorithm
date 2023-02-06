import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static char[][] board = new char[6][6];
    static List<Teacher> teachers = new ArrayList<>();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Teacher {
        int x, y;

        public Teacher(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                String s = st.nextToken();
                board[i][j] = s.charAt(0);
                if (board[i][j] == 'T') {
                    teachers.add(new Teacher(i, j));
                }
            }
        }

        placeObstacle(0, 0, 0);
        System.out.println("NO");
    }

    private static void placeObstacle(int x, int y, int cnt) {
        if (cnt == 3) {
            if (canEscape()) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }
        if (x == N) {
            return;
        }
        if (y == N) {
            placeObstacle(x + 1, 0, cnt);
            return;
        }
        if (board[x][y] == 'X') {
            board[x][y] = 'O';
            placeObstacle(x, y + 1, cnt + 1);
            board[x][y] = 'X';
        }
        placeObstacle(x, y + 1, cnt);
    }

    private static boolean canEscape() {
        for (Teacher teacher : teachers) {
            for (int i = 0; i < 4; i++) {
                int nx = teacher.x + dx[i];
                int ny = teacher.y + dy[i];
                while (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if (board[nx][ny] == 'O' || board[nx][ny] == 'T') break;
                    if (board[nx][ny] == 'S') return false;
                    nx += dx[i];
                    ny += dy[i];
                }
            }
        }
        return true;
    }
}