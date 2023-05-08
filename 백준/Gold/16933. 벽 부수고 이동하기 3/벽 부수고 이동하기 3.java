import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static char[][] board = new char[1001][1001];
    static boolean[][][][] visit = new boolean[1001][1001][11][2];   // (x, y, 벽 부순 횟수, 낮 여부)
    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};
    static class Case {
        int x, y, cnt, isDay;    // (x, y), 이동거리, 벽 부순 횟수, 낮 여부

        public Case(int x, int y, int cnt, int isDay) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.isDay = isDay;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            String row = br.readLine();
            for (int j = 1; j <= M; j++) {
                board[i][j] = row.charAt(j - 1);
            }
        }

        int time = 1;
        Queue<Case> que = new LinkedList<>();
        que.offer(new Case(1, 1, 0, 1));
        visit[1][1][0][1] = true;
        while (!que.isEmpty()) {
            int size = que.size();
            while (size-- > 0) {
                Case cur = que.poll();
                if (cur.x == N && cur.y == M) {
                    System.out.println(time);
                    return;
                }
                int nxtDay = (cur.isDay + 1) % 2;
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (nx < 1 || nx > N || ny < 1 || ny > M) continue;
                    int nxtCnt = cur.cnt;
                    if (board[nx][ny] == '1') {
                        if (cur.isDay == 0) continue;
                        if (++nxtCnt > K) continue;
                    }
                    if (visit[nx][ny][nxtCnt][nxtDay]) continue;
                    que.offer(new Case(nx, ny, nxtCnt, nxtDay));
                    visit[nx][ny][nxtCnt][nxtDay] = true;
                }
                if (visit[cur.x][cur.y][cur.cnt][nxtDay]) continue;
                que.offer(new Case(cur.x, cur.y, cur.cnt, nxtDay));
                visit[cur.x][cur.y][cur.cnt][nxtDay] = true;
            }
            time++;
        }
        System.out.println(-1);
    }
}