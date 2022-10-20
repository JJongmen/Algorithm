import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] board = new int[55][55];
    static boolean[][] check; // 구름이 사라졌던 칸이면 true
    static Queue<Position> clouds = new LinkedList<>();
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};

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
        int M = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds.offer(new Position(N, 1));
        clouds.offer(new Position(N, 2));
        clouds.offer(new Position(N - 1, 1));
        clouds.offer(new Position(N - 1, 2));

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());   // 방향
            int s = Integer.parseInt(st.nextToken());   // 거리
            Queue<Position> clouds2 = new LinkedList<>();
            check = new boolean[N + 1][N + 1];
            while (!clouds.isEmpty()) {
                // 1. 모든 구름을 이동시킨다.
                Position cloud = clouds.poll();
                int nx = (N + cloud.x + dx[d] * s % N) % N;
                int ny = (N + cloud.y + dy[d] * s % N) % N;
                if (nx == 0) nx = N;
                if (ny == 0) ny = N;
                clouds2.offer(new Position(nx, ny));
                check[nx][ny] = true;
                // 2. 각 구름에서 비가 내려 물의 양이 1 증가한다.
                board[nx][ny] += 1;
            }

            // 3. 물복사버그 마법 사용
            while (!clouds2.isEmpty()) {
                Position cloud = clouds2.poll();
                int cnt = 0;
                for (int k = 2; k <= 8; k += 2) {
                    int nx = cloud.x + dx[k];
                    int ny = cloud.y + dy[k];
                    if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
                    if (board[nx][ny] > 0) cnt++;
                }
                board[cloud.x][cloud.y] += cnt;
            }
            
            // 4. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고 물의 양이 2 줄어든다
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (board[i][j] >= 2 && !check[i][j]) {
                        clouds.offer(new Position(i, j));
                        board[i][j] -= 2;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                result += board[i][j];
            }
        }
        System.out.println(result);
    }
}