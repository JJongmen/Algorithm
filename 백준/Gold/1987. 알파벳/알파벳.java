import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static char[][] board;
    private static int R;
    private static int C;
    private static boolean[] alphabets = new boolean[26];
    private static int max = 0;

    private static boolean check(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }

    private static void dfs(int x, int y, int cnt) {
        alphabets[board[x][y] - 'A'] = true;
        max = Math.max(max, cnt);
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (check(nextX, nextY) && !alphabets[board[x + dx[i]][y + dy[i]] - 'A']) {
                dfs(nextX, nextY, cnt + 1);
                alphabets[board[x + dx[i]][y + dy[i]] - 'A'] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        dfs(0, 0, 1);
        System.out.println(max);
    }
}