import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] board = new boolean[105][105];
    static int[] directions = new int[1030];

    // 시작 방향이 0일 때의 방향들을 directons 배열에 저장한다.
    static {
        for (int g = 1; g <= 10; g++) {
            int k = 1 << (g - 1);
            for (int i = 0; i < k; i++) {
                directions[k + i] = (directions[k - 1 - i] + 1) % 4;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            int nx = x;
            int ny = y;
            board[nx][ny] = true;
            for (int i = 0; i < (1 << g); i++) {
                nx += dx[(directions[i] + d) % 4];
                ny += dy[(directions[i] + d) % 4];
                board[nx][ny] = true;
            }
        }

        int result = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (!board[i][j]) continue;
                if (!board[i + 1][j]) continue;
                if (!board[i][j + 1]) continue;
                if (!board[i + 1][j + 1]) continue;
                result++;
            }
        }
        System.out.println(result);
    }
}