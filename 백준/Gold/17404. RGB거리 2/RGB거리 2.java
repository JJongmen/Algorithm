import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] colors = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            colors[i][0] = Integer.parseInt(st.nextToken());
            colors[i][1] = Integer.parseInt(st.nextToken());
            colors[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] memo = new int[N][3];
        int min = INF;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    memo[0][j] = colors[0][j];
                } else {
                    memo[0][j] = INF;
                }
            }
            for (int j = 1; j < N; j++) {
                memo[j][0] = Math.min(memo[j - 1][1], memo[j - 1][2]) + colors[j][0];
                memo[j][1] = Math.min(memo[j - 1][0], memo[j - 1][2]) + colors[j][1];
                memo[j][2] = Math.min(memo[j - 1][0], memo[j - 1][1]) + colors[j][2];
            }
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    min = Math.min(min, memo[N - 1][j]);
                }
            }
            memo = new int[N][3];
        }

        System.out.println(min);
    }
}