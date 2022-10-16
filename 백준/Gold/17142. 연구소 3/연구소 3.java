import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int IMPOSSIBLE = 10000;
    static int N, M;
    static int emptyCnt;    // 총 빈 칸의 개수
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] board = new int[53][53];
    static int[] virusIdx = new int[13];
    static List<Position> viruses = new ArrayList<>();
    static int result = IMPOSSIBLE;

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void bt(int cur, int lastUsedIdx) {
        if (cur == M) {
            bfs();
            return;
        }
        for (int i = lastUsedIdx + 1; i < viruses.size(); i++) {
            virusIdx[cur] = i;
            bt(cur + 1, i);
        }
    }

    static void bfs() {
        int[][] tmpBoard = new int[53][53];
        int infectCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmpBoard[i][j] = board[i][j];
            }
        }

        Queue<Position> que = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            Position virus = viruses.get(virusIdx[i]);
            tmpBoard[virus.x][virus.y] = 1;
            que.offer(virus);
        }

        while (!que.isEmpty()) {
            Position cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (tmpBoard[nx][ny] == 0 || tmpBoard[nx][ny] == -2) {
                    if (tmpBoard[nx][ny] == 0) infectCnt++;
                    tmpBoard[nx][ny] = tmpBoard[cur.x][cur.y] + 1;
                    que.offer(new Position(nx, ny));
                    if (infectCnt == emptyCnt) {
                        result = Math.min(result, tmpBoard[nx][ny] - 1);
                        return;
                    }
                }
            }
        }
    }

    private static void printBoard(int[][] tmpBoard) {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(tmpBoard[i]));
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int info = Integer.parseInt(st.nextToken());
                if (info == 0) {
                    emptyCnt++;
                } else if (info == 1) {
                    board[i][j] = -1;   // 벽
                } else if (info == 2) {
                    board[i][j] = -2;   // 비활성 바이러스
                    viruses.add(new Position(i, j));
                }
            }
        }
        if (emptyCnt == 0) {
            System.out.println(0);
            return;
        }

        bt(0, -1);

        System.out.println(result == IMPOSSIBLE ? -1 : result);
    }
}