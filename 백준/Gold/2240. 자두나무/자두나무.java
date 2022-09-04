import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] trees = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            trees[i] = Integer.parseInt(br.readLine());
        }

        int[][] memo = new int[T + 1][W + 1];
        for (int i = 1; i <= T; i++) {
            memo[i][0] = memo[i - 1][0];
            if (trees[i] == 1) {
                memo[i][0] += 1;
            }
        }

        for (int j = 1; j <= W; j++) {
            for (int i = 1; i <= T; i++) {
                memo[i][j] = Math.max(memo[i - 1][j], memo[i - 1][j - 1]);
                if (trees[i] == 1 && j % 2 == 0 || trees[i] == 2 && j % 2 == 1) {
                    memo[i][j] += 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i <= W; i++) {
            max = Math.max(max, memo[T][i]);
        }

        System.out.println(max);
    }
}