import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int M, N, K;
    static int[][] board = new int[105][105];
    static List<Integer> result = new ArrayList<>();

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
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int i = y1; i < y2; i++) {
                for (int j = x1; j < x2; j++) {
                    board[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    result.add(bfs(i, j));
                }
            }
        }

        System.out.println(result.size());
        StringBuilder sb = new StringBuilder();
        Collections.sort(result);
        for (Integer area : result) {
            sb.append(area).append(" ");
        }
        System.out.println(sb);
    }

    private static int bfs(int sx, int sy) {
        Queue<Position> que = new LinkedList<>();
        que.offer(new Position(sx, sy));
        board[sx][sy] = 1;
        int area = 0;
        while (!que.isEmpty()) {
            Position cur = que.poll();
            area++;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
                if (board[nx][ny] == -1 || board[nx][ny] > 0) continue;
                que.offer(new Position(nx, ny));
                board[nx][ny] = 1;
            }
        }
        return area;
    }
}