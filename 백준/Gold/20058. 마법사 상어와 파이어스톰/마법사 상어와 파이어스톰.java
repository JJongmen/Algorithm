import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, Q;
    static int[][] board = new int[100][100];
    static boolean[][] minus = new boolean[100][100];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visit = new boolean[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        for (int i = 0; i < (1 << N); i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < (1 << N); j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while (Q-- > 0) {
            int L = Integer.parseInt(st.nextToken());
            fireStorm(L);
        }

        int sum = 0;
        int maxCnt = 0;
        for (int i = 0; i < (1 << N); i++) {
            for (int j = 0; j < (1 << N); j++) {
                if (board[i][j] == 0) continue;
                sum += board[i][j];
                if (visit[i][j]) continue;
                maxCnt = Math.max(maxCnt, dfs(i, j));
            }
        }
        System.out.println(sum);
        System.out.println(maxCnt);
    }

    private static int dfs(int x, int y) {
        visit[x][y] = true;
        int cnt = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= (1 << N) || ny < 0 || ny >= (1 << N)) continue;
            if (visit[nx][ny] || board[nx][ny] == 0) continue;
            cnt += dfs(nx, ny);
        }
        return cnt;
    }

    private static void fireStorm(int level) {
        for (int i = 0; i < (1 << N); i += (1 << level)) {
            for (int j = 0; j < (1 << N); j += (1 << level)) {
                turn(i, j, 1 << level);
            }
        }

        for (int i = 0; i < (1 << N); i++) {
            for (int j = 0; j < (1 << N); j++) {
                if (board[i][j] == 0) continue;
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || nx >= (1 << N) || ny < 0 || ny >= (1 << N)) continue;
                    if (board[nx][ny] == 0) continue;
                    cnt++;
                }
                if (cnt < 3) minus[i][j] = true;
            }
        }

        for (int i = 0; i < (1 << N); i++) {
            for (int j = 0; j < (1 << N); j++) {
                if (minus[i][j]) {
                    board[i][j]--;
                    minus[i][j] = false;
                }
            }
        }
    }

    private static void turn(int x, int y, int len) {
        int[][] tmp = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                tmp[i][j] = board[x + i][y + j];
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                board[x + j][y + len - 1 - i] = tmp[i][j];
            }
        }
    }
}