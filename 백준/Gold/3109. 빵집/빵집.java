import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 0, 1};
    static int R, C;
    static char[][] board;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        visit = new boolean[R][C];
        int result = 0;
        for (int i = 0; i < R; i++) {
            result += startRow(i);
        }
        System.out.println(result);
    }

    private static int startRow(int row) {
        if (dfs(row, 0)) {
            return 1;
        }
        return 0;
    }

    private static boolean dfs(int cx, int cy) {
        visit[cx][cy] = true;
        board[cx][cy] = 'x';
        if (cy == C - 1) {
            return true;
        }
        for (int i = 0; i < 3; i++) {
            int nx = cx + dx[i];
            int ny = cy + 1;
            if (nx < 0 || nx >= R) continue;
            if (board[nx][ny] == 'x' || visit[nx][ny]) continue;
            if (dfs(nx, ny)) return true;
            board[nx][ny] = '.';
        }
        return false;
    }
}