import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    // 동, 서, 남, 북
    static double[] probability = new double[4];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visit = new boolean[30][30]; // 로봇의 시작 위치 = (15,15)
    static double result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            probability[i] = Integer.parseInt(st.nextToken()) / 100D;
        }

        dfs(15, 15, 0, 1);

        System.out.printf("%.9f\n", result);
    }

    private static void dfs(int x, int y, int moveCnt, double percent) {
        if (visit[x][y]) {
            return;
        }
        if (moveCnt == N) {
            result += percent;
            return;
        }
        visit[x][y] = true;
        for (int i = 0; i < 4; i++) {
            dfs(x + dx[i], y + dy[i], moveCnt + 1, percent * probability[i]);
        }
        visit[x][y] = false;
    }
}