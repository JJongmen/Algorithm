import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] board = new int[20][20];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N;
    static int cx, cy;      // 현재 아기 상어의 위치 좌표
    static int size = 2;    // 현재 아기 상어의 크기
    static int eatCnt;      // 현재 아기 상어가 먹은 물고기 수 (크기가 커지면 0으로 초기화)
    static int result;

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    cx = i;
                    cy = j;
                    board[i][j] = 0;
                }
            }
        }

        while (moveAndEat());
        System.out.println(result);

    }

    static boolean moveAndEat() {
        Queue<Position> que = new LinkedList<>();
        int[][] dist = new int[N][N];
        dist[cx][cy] = 1;
        que.offer(new Position(cx, cy));
        Queue<Position> fishes = new PriorityQueue<>((o1, o2) -> {
            if (o1.x == o2.x) return o1.y - o2.y;
            return o1.x - o2.x;
        });
        int time = -1;
        while (!que.isEmpty()) {
            Position cur = que.poll();
            if (time == dist[cur.x][cur.y] - 1) break;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (dist[nx][ny] != 0 || board[nx][ny] > size) continue;
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
                que.offer(new Position(nx, ny));
                if (board[nx][ny] > 0 && board[nx][ny] < size) {
                    time = dist[nx][ny] - 1;
                    fishes.offer(new Position(nx, ny));
                }
            }
        }
        if (fishes.size() == 0) {
            return false;
        } else {
            Position target = fishes.poll();
            board[target.x][target.y] = 0;
            cx = target.x;
            cy = target.y;
            result += time;
            eatCnt++;
            if (size == eatCnt) {
                size++;
                eatCnt = 0;
            }
            return true;
        }
    }
}