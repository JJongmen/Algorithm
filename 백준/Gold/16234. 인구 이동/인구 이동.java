import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] board = new int[50][50];
    static boolean[][] visit;
    static List<Position> union;

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 연합이 있는지 확인하며 연합이 있다면 각 칸의 인구수를 반환한다.
     * 만약 연합이 없다면 -1을 반환한다.
     */
    static boolean bfs(int sx, int sy) {
        Queue<Position> que = new LinkedList<>();
        int sum = board[sx][sy];
        union = new ArrayList<>();
        visit[sx][sy] = true;
        Position start = new Position(sx, sy);
        que.offer(start);
        union.add(start);
        while (!que.isEmpty()) {
            Position cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visit[nx][ny]) continue;
                int diff = Math.abs(board[nx][ny] - board[cur.x][cur.y]);
                if (diff < L || diff > R) continue;
                visit[nx][ny] = true;
                sum += board[nx][ny];
                Position next = new Position(nx, ny);
                que.offer(next);
                union.add(next);
            }
        }

        // 연합이 있는 경우 연합의 인원 수를 분배한다.
        if (union.size() > 1) {
            movePeople(sum / union.size());
            return true;
        }
        return false;
    }

    // 인구이동을 시킨다
    static void movePeople(int population) {
        for (Position cur : union) {
            board[cur.x][cur.y] = population;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        while (true) {
            boolean finishFlag = true;
            visit = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visit[i][j]) continue;
                    if (bfs(i, j)) {
                        finishFlag = false;
                    }
                }
            }
            if (finishFlag) {
                break;
            }
            result++;
        }
        System.out.println(result);
    }
}