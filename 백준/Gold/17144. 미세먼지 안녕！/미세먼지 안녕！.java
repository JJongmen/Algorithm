import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int cx;  // 아래쪽 공기 청정기의 x좌표
    static int[][] room = new int[55][55];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Dust {
        int x, y;
        int amount;

        public Dust(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) cx = i;
            }
        }

        while (T-- > 0) {
            diffuse();
            circulate();
        }
        System.out.println(getResult());
    }

    static int getResult() {
        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] > 0) result += room[i][j];
            }
        }
        return result;
    }

    static void circulate() {
        circulateUp();
        circulateDown();
    }

    static void circulateUp() {
        for (int i = cx - 2; i > 0; i--) {
            room[i][0] = room[i - 1][0];
        }
        for (int j = 0; j < C - 1; j++) {
            room[0][j] = room[0][j + 1];
        }
        for (int i = 0; i < cx - 1; i++) {
            room[i][C - 1] = room[i + 1][C - 1];
        }
        for (int j = C - 1; j > 0; j--) {
            room[cx - 1][j] = room[cx - 1][j - 1];
        }
        room[cx - 1][1] = 0;
    }

    static void circulateDown() {
        for (int i = cx + 1; i < R - 1; i++) {
            room[i][0] = room[i + 1][0];
        }
        for (int j = 0; j < C - 1; j++) {
            room[R - 1][j] = room[R - 1][j + 1];
        }
        for (int i = R - 1; i > cx; i--) {
            room[i][C - 1] = room[i - 1][C - 1];
        }
        for (int j = C - 1; j > 0; j--) {
            room[cx][j] = room[cx][j - 1];
        }
        room[cx][1] = 0;
    }

    // 미세먼지를 확산시키는 메서드
    static void diffuse() {
        Queue<Dust> que = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] > 0) que.offer(new Dust(i, j, room[i][j]));
            }
        }

        while (!que.isEmpty()) {
            Dust dust = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dust.x + dx[i];
                int ny = dust.y + dy[i];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                if (room[nx][ny] == -1) continue;
                room[nx][ny] += dust.amount / 5;
                room[dust.x][dust.y] -= dust.amount / 5;
            }
        }
    }
}