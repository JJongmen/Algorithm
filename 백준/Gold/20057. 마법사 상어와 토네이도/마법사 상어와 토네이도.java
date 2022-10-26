import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 왼쪽, 아래, 오른쪽, 위
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[] route = new int[250000];   //토네이도의 이동경로
    static int[][] board = new int[500][500];
    static int N;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 토네이도의 이동 경로를 route 배열에 저장한다.
        setRoute();
        // 토네이도를 (N / 2, N / 2) 위치에서부터 이동시킨다.
        moveTonado();

        System.out.println(result);
    }

    private static void moveTonado() {
        int x = N / 2;
        int y = N / 2;
        for (int i = 0; i < N * N - 1; i++) {
            int d = route[i];
            scatterSand(x, y, d);
            x += dx[d];
            y += dy[d];
        }
    }

    static void setRoute() {
        int k = 0;
        int dir = 0;    // 방향 (초기 방향 : 왼쪽)
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                route[k++] = dir;
            }
            dir = (dir + 1) % 4;
            for (int j = 0; j < i; j++) {
                route[k++] = dir;
            }
            dir = (dir + 1) % 4;
        }
        for (int i = 0; i < N - 1; i++) {
            route[k++] = dir;
        }
    }

    // (x,y) 위치에서 d 방향으로 토네이도가 움직일 때 모래를 흩날리는 메서드
    static void scatterSand(int x, int y, int d) {
        int totalSand = board[x + dx[d]][y + dy[d]];
        board[x + dx[d]][y + dy[d]] = 0;
        int minusSand = 0;
        minusSand += processSand(x + dx[(d + 3) % 4], y + dy[(d + 3) % 4], totalSand / 100);
        minusSand += processSand(x + dx[(d + 1) % 4], y + dy[(d + 1) % 4], totalSand / 100);
        x += dx[d];
        y += dy[d];
        minusSand += processSand(x + dx[(d + 3) % 4], y + dy[(d + 3) % 4], totalSand * 7 / 100);
        minusSand += processSand(x + dx[(d + 1) % 4], y + dy[(d + 1) % 4], totalSand * 7 / 100);
        minusSand += processSand(x + dx[(d + 3) % 4] * 2, y + dy[(d + 3) % 4] * 2, totalSand / 50);
        minusSand += processSand(x + dx[(d + 1) % 4] * 2, y + dy[(d + 1) % 4] * 2, totalSand / 50);
        x += dx[d];
        y += dy[d];
        minusSand += processSand(x + dx[(d + 3) % 4], y + dy[(d + 3) % 4], totalSand / 10);
        minusSand += processSand(x + dx[(d + 1) % 4], y + dy[(d + 1) % 4], totalSand / 10);
        minusSand += processSand(x + dx[d], y + dy[d], totalSand / 20);
        processSand(x, y, totalSand - minusSand);
    }

    // 모래가 경계 밖으로 흩날리면 result 에 모래를 더하고 아니면 배열에 모래의 양을 더한다.
    static int processSand(int nx, int ny, int sand) {
        if (nx < 0 || nx >= N || ny < 0 || ny >= N) result += sand;
        else board[nx][ny] += sand;
        return sand;
    }
}