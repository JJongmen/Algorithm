import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[10][10];
    static boolean[] rCheck = new boolean[20];
    static boolean[] lCheck = new boolean[20];
    static int N;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        fill(0, 0, 0);
        result += max;
        max = 0;
        fill(0, 1, 0);
        result += max;
        System.out.println(result);
    }

    private static void fill(int x, int y, int cnt) {
        if (x == N) {
            max = Math.max(max, cnt);
            return;
        }
        if (y >= N) {
            if (N % 2 == 0) {
                if (y == N) {
                    fill(x + 1, 1, cnt);
                } else {
                    fill(x + 1, 0, cnt);
                }
            } else {
                if (y == N) {
                    fill(x + 1, 0, cnt);
                } else {
                    fill(x + 1, 1, cnt);
                }
            }
            return;
        }
        if (board[x][y] != 0 && !rCheck[x + y] && !lCheck[N - 1 - x + y]) {
            rCheck[x + y] = true;
            lCheck[N - 1 - x + y] = true;
            fill(x, y + 2, cnt + 1);
            rCheck[x + y] = false;
            lCheck[N - 1 - x + y] = false;
        }
        fill(x, y + 2, cnt);
    }
}