import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // (1, 2, 3, 4, 5, 6, 7, 8) = (↑, ↖, ←, ↙, ↓, ↘, →, ↗)
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[][] board = new int[4][4];   // 물고기의 번호를 저장한다. -1이면 상어가 있는 경우, 0이면 물고기가 먹힌 경우
    static Info[] fishes = new Info[17];    // fishes[i] = i번 물고기의 좌표와 방향을 저장한다.
    static Info shark = new Info(0, 0, 0);
    static int result;

    static class Info {
        int x, y;   // 좌표
        int d;  //방향

        public Info(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                board[i][j] = num;
                fishes[num] = new Info(i, j, d);
            }
        }
        // 상어가 (0, 0)에 있는 물고기를 먹고, (0, 0)에 들어간다.
        // 상어의 방향은 (0, 0)에 있던 물고기의 방향과 같다.
        int num = board[0][0];
        eatFish(0, 0);
        cycle(num);

        System.out.println(result);
    }

    static void cycle(int sum) {
        // 물고기가 이동한다.
        moveFishes();
        // 상어가 이동하면서 먹을 수 있는 물고기 번호의 합의 최댓값을 구한다.
        Info beforeShark = new Info(shark.x, shark.y, shark.d);
        Info[] beforeFishes = new Info[17];
        int[][] beforeBoard = new int[4][4];
        beforeProcess(beforeFishes, beforeBoard);
        int nx = shark.x + dx[shark.d];
        int ny = shark.y + dy[shark.d];
        while (nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
            int num = board[nx][ny];
            if (num == 0) {         // 물고기가 없는 칸은 이동할 수 없다.
                nx += dx[shark.d];
                ny += dy[shark.d];
                continue;
            }
            eatFish(nx, ny);
            cycle(sum + num);
            afterProcess(beforeShark, beforeFishes, beforeBoard);
            nx += dx[shark.d];
            ny += dy[shark.d];
        }
        result = Math.max(result, sum);
    }

    private static void beforeProcess(Info[] beforeFishes, int[][] beforeBoard) {
        for (int i = 1; i <= 16; i++) {
            Info fish = fishes[i];
            if (fish == null) {
                beforeFishes[i] = null;
                continue;
            }
            beforeFishes[i] = new Info(fish.x, fish.y, fish.d);
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                beforeBoard[i][j] = board[i][j];
            }
        }
    }

    private static void afterProcess(Info beforeShark, Info[] beforeFishes, int[][] beforeBoard) {
        shark = new Info(beforeShark.x, beforeShark.y, beforeShark.d);
        for (int i = 1; i <= 16; i++) {
            Info fish = beforeFishes[i];
            if (fish == null) {
                beforeFishes[i] = null;
                continue;
            }
            fishes[i] = new Info(fish.x, fish.y, fish.d);
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = beforeBoard[i][j];
            }
        }
    }

    // 상어가 (x, y) 위치에 있는 물고기를 먹는다.
    private static void eatFish(int x, int y) {
        int num = board[x][y];
        board[shark.x][shark.y] = 0;
        shark = new Info(x, y, fishes[num].d);
        fishes[num] = null;
        board[x][y] = -1;
    }

    private static void printBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%3d", board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void moveFishes() {
        for (int num = 1; num <= 16; num++) {
            Info fish = fishes[num];
            if (fish == null) continue; // 이미 물고기가 먹힌 경우
            for (int k = 0; k < 8; k++) {
                int nd = (fish.d + k) % 8;
                if (nd == 0) nd = 8;
                int nx = fish.x + dx[nd];
                int ny = fish.y + dy[nd];
                if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;   // 공간의 경계를 넘는 경우
                if (board[nx][ny] == -1) continue;  // 상어가 있는 경우
                int otherNum = board[nx][ny];
                Info otherFish = fishes[otherNum];
                board[nx][ny] = num;
                board[fish.x][fish.y] = otherNum;
                if (otherNum != 0) {
                    otherFish.x = fish.x;
                    otherFish.y = fish.y;
                }
                fish.x = nx;
                fish.y = ny;
                fish.d = nd;
                break;
            }
        }
    }
}