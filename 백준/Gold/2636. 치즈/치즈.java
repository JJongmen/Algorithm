import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N, M;
    static int[][] board;

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 1;
        int prev = countCheese();
        while (true) {
            checkAir();
            removeCheese();
            int cnt = countCheese();
            if (cnt == 0) break;
            prev = cnt;
            time++;
        }
        System.out.println(time);
        System.out.println(prev);
    }

    private static int countCheese() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) cnt++;
            }
        }
        return cnt;
    }

    private static void removeCheese() {
        boolean[][] willRemove = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != 1) continue;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (board[nx][ny] != -1) continue;
                    willRemove[i][j] = true;
                    break;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (willRemove[i][j]) {
                    board[i][j] = -1;
                }
            }
        }
    }

    private static void printBoard() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println();
    }

    private static void checkAir() {
        Queue<Position> que = new LinkedList<>();
        que.offer(new Position(0, 0));
        board[0][0] = -1;
        boolean[][] visit = new boolean[N][M];
        visit[0][0] = true;
        while (!que.isEmpty()) {
            Position cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (board[nx][ny] == 1 || visit[nx][ny]) continue;
                que.offer(new Position(nx, ny));
                board[nx][ny] = -1;
                visit[nx][ny] = true;
            }
        }
    }
}