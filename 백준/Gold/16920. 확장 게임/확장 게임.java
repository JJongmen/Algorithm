import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, P;
    static int[][] board = new int[1000][1000];
    static int[] move = new int[11];
    static Queue<Position>[] que = new Queue[11];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[] result = new int[11];

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
        P = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= P; i++) {
            move[i] = Integer.parseInt(st.nextToken());
            que[i] = new LinkedList<>();
        }
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                char ch = row.charAt(j);
                if (ch == '.') board[i][j] = 0;
                else if (ch == '#') board[i][j] = -1;
                else {
                    int player = ch - '0';
                    board[i][j] = player;
                    result[player]++;
                    que[player].add(new Position(i, j));
                }
            }
        }

        boolean flag = true;
        while (flag) {
            flag = false;
            for (int player = 1; player <= P; player++) {
                flag |= bfs(player);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= P; i++) {
            sb.append(result[i]).append(' ');
        }
        System.out.println(sb);
    }

    private static boolean bfs(int player) {
        if (que[player].isEmpty()) return false;
        int moveDist = move[player];
        while (moveDist-- > 0) {
            int size = que[player].size();
            while (size-- > 0) {
                Position cur = que[player].poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    if (board[nx][ny] != 0) continue;
                    board[nx][ny] = player;
                    result[player]++;
                    que[player].offer(new Position(nx, ny));
                }
            }
            if (que[player].isEmpty()) return true;
        }
        return true;
    }
}