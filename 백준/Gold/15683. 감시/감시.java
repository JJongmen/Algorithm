import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // 오른쪽, 아래, 왼쪽, 위
    private static final int RIGHT = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int UP = 3;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static int[][][] cctvDirections = {
            {},
            {{RIGHT}, {DOWN}, {LEFT}, {UP}},
            {{RIGHT, LEFT}, {DOWN, UP}},
            {{UP, RIGHT}, {RIGHT, DOWN}, {DOWN, LEFT}, {LEFT, UP}},
            {{LEFT, UP, RIGHT}, {UP, RIGHT, DOWN}, {RIGHT, DOWN, LEFT}, {DOWN, LEFT, UP}},
            {{UP, RIGHT, DOWN, LEFT}}
    };
    private static int N;
    private static int M;
    private static int[][] board;
    private static List<Position> cctvs;
    private static int answer = 64;

    private static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void setDirection(int cur) {
        if (cur == cctvs.size()) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == 0) cnt++;
                }
            }
            answer = Math.min(answer, cnt);
            return;
        }
        Position cctv = cctvs.get(cur);
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp[i] = board[i].clone();
        }
        int cctvKind = board[cctv.x][cctv.y];
        for (int i = 0; i < cctvDirections[cctvKind].length; i++) {
            for (int j = 0; j < cctvDirections[cctvKind][i].length; j++) {
                observe(cctv, cctvDirections[cctvKind][i][j]);
            }
            setDirection(cur + 1);
            for (int j = 0; j < N; j++) {
                board[j] = temp[j].clone();
            }
        }
    }

    private static void observe(Position cctv, int direction) {
        int nx = cctv.x + dx[direction];
        int ny = cctv.y + dy[direction];
        while (0 <= nx && nx < N && 0 <= ny && ny < M) {
            if (board[nx][ny] == 6) break;
            if (board[nx][ny] == 0) board[nx][ny] = -1;
            nx += dx[direction];
            ny += dy[direction];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        cctvs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0 || board[i][j] == 6) continue;
                cctvs.add(new Position(i, j));
            }
        }
        setDirection(0);
        System.out.println(answer);
    }
}