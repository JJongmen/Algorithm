import java.io.*;
import java.util.*;

public class Main {
    static char[][] board = new char[105][105];
    static boolean[] hasKey = new boolean[26];
    static Queue<Position>[] door = new LinkedList[26];
    static boolean[][] visit = new boolean[105][105];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int H, W;

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            for (int i = 1; i <= H; i++) {
                String row = br.readLine();
                for (int j = 1; j <= W; j++) {
                    board[i][j] = row.charAt(j - 1);
                }
            }

            for (int i = 0; i <= W + 1; i++) {
                board[0][i] = '.';
                board[H + 1][i] = '.';
            }
            for (int i = 1; i <= H; i++) {
                board[i][0] = '.';
                board[i][W + 1] = '.';
            }

            Arrays.fill(hasKey, false);
            String keys = br.readLine();
            if (!"0".equals(keys)) {
                for (int i = 0; i < keys.length(); i++) {
                    hasKey[keys.charAt(i) - 'a'] = true;
                }
            }

            for (int i = 0; i <= H + 1; i++) {
                Arrays.fill(visit[i], 0, W + 2, false);
            }
            Queue<Position> que = new LinkedList<>();
            que.offer(new Position(0, 0));
            visit[0][0] = true;

            for (int i = 0; i < 26; i++) {
                door[i] = new LinkedList<>();
            }

            int result = 0;
            while (!que.isEmpty()) {
                Position cur = que.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (nx < 0 || nx > H + 1 || ny < 0 || ny > W + 1) continue;
                    if (visit[nx][ny] || board[nx][ny] == '*') continue;
                    visit[nx][ny] = true;
                    if ('A' <= board[nx][ny] && board[nx][ny] <= 'Z' && !hasKey[board[nx][ny] - 'A']) {
                        door[board[nx][ny] - 'A'].offer(new Position(nx, ny));
                        continue;
                    }
                    if ('a' <= board[nx][ny] && board[nx][ny] <= 'z' && !hasKey[board[nx][ny] - 'a']) {
                        while (!door[board[nx][ny] - 'a'].isEmpty()) {
                            que.offer(door[board[nx][ny] - 'a'].poll());
                        }
                        hasKey[board[nx][ny] - 'a'] = true;
                    }
                    if (board[nx][ny] == '$') {
                        result++;
                    }
                    que.offer(new Position(nx, ny));
                }
            }
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
