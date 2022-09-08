import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[][] memo;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int M;
    static int N;

    static boolean checkMap(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    static int dfs(int x, int y) {
        if (memo[x][y] != -1) {
            return memo[x][y];
        }
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (checkMap(nextX, nextY) && map[x][y] > map[nextX][nextY]) {
                cnt += dfs(nextX, nextY);
            }
        }
        return memo[x][y] = cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        memo = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                memo[i][j] = -1;
            }
        }
        memo[M - 1][N - 1] = 1;

        System.out.println(dfs(0, 0));
    }
}