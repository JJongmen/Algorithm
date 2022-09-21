import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    private static class Position {
        int x;
        int y;
        boolean canBreak;

        public Position(int x, int y, boolean canBreak) {
            this.x = x;
            this.y = y;
            this.canBreak = canBreak;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][][] board = new int[N][M][2];
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < M; j++) {
                char ch = temp.charAt(j);
                if (ch == '0') {
                    board[i][j][0] = 0;
                    board[i][j][1] = 0;
                } else {
                    board[i][j][0] = -1;
                    board[i][j][1] = -1;
                }
            }
        }

        if (N == 1 && M == 1) {
            System.out.println(1);
            return;
        }

        Queue<Position> que = new LinkedList<>();
        board[0][0][0] = 1;
        board[0][0][1] = 1;
        que.offer(new Position(0, 0, true));
        while (!que.isEmpty()) {
            Position current = que.poll();
            boolean canBreak = current.canBreak;
            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];
                if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
                    if (canBreak) {
                        if (board[nextX][nextY][1] == 0) {
                            que.offer(new Position(nextX, nextY, true));
                            board[nextX][nextY][1] = board[current.x][current.y][1] + 1;
                            if (nextX == N - 1 && nextY == M - 1) {
                                System.out.println(board[nextX][nextY][1]);
                                return;
                            }
                        } else if (board[nextX][nextY][1] == -1) {
                            que.offer(new Position(nextX, nextY, false));
                            board[nextX][nextY][0] = board[current.x][current.y][1] + 1;
                        }
                    } else {
                        if (board[nextX][nextY][0] == 0) {
                            que.offer(new Position(nextX, nextY, false));
                            board[nextX][nextY][0] = board[current.x][current.y][0] + 1;
                            if (nextX == N - 1 && nextY == M - 1) {
                                System.out.println(board[nextX][nextY][0]);
                                return;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
