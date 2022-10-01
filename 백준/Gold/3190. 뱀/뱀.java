import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // (0, 1, 2, 3) = (오른쪽, 아래, 왼쪽, 위)
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static int[][] board = new int[102][102];
    private static boolean[][] apple = new boolean[102][102];
    private static char[] turnInfo = new char[10002];
    static {
        for (int i = 0; i < 102; i++) {
            Arrays.fill(board[i], -1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            apple[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }
        int L = Integer.parseInt(br.readLine());
        while (L-- > 0) {
            st = new StringTokenizer(br.readLine());
            turnInfo[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0);
        }

        int headX = 1;
        int headY = 1;
        int tailX = 1;
        int tailY = 1;
        int headD = 0;
        int tailD;
        board[1][1] = 0;
        for (int time = 1; time <= 10000; time++) {
            int nx = headX + dx[headD];
            int ny = headY + dy[headD];
            // 종료 조건
            if (nx < 1 || nx > N || ny < 1 || ny > N || board[nx][ny] != -1) {
                System.out.println(time);
                return;
            }

            // 방향 변환 정보에 따라 방향 변경
            if (turnInfo[time] == 'D') {
                headD = (headD + 1) % 4;
            } else if (turnInfo[time] == 'L') {
                headD = (headD + 3) % 4;
            }
            board[nx][ny] = headD;

            headX = nx;
            headY = ny;

            if (apple[headX][headY]) {
                apple[headX][headY] = false;
                continue;
            }
            // 사과가 없으면 꼬리를 이동시킴
            tailD = board[tailX][tailY];
            board[tailX][tailY] = -1;
            tailX += dx[tailD];
            tailY += dy[tailD];
        }
        System.out.println(10000);
    }
}