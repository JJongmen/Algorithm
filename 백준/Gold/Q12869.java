import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q12869 {
    public static final int INF = 100;
    static int N;
    static int[] scv = new int[3];
    static int[][][] memo = new int[61][61][61];

    static {
        for (int i = 0; i <= 60; i++) {
            for (int j = 0; j <= 60; j++) {
                Arrays.fill(memo[i][j], INF);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(attack(scv[0], scv[1], scv[2]));
    }

    private static int attack(int a, int b, int c) {
        if (a < 0) a = 0;
        if (b < 0) b = 0;
        if (c < 0) c = 0;
        if (a == 0 && b == 0 && c == 0) {
            return 0;
        }
        if (memo[a][b][c] != INF) {
            return memo[a][b][c];
        }
        int result = INF;
        result = Math.min(result, attack(a - 9, b - 3, c - 1) + 1);
        result = Math.min(result, attack(a - 9, b - 1, c - 3) + 1);
        result = Math.min(result, attack(a - 3, b - 9, c - 1) + 1);
        result = Math.min(result, attack(a - 3, b - 1, c - 9) + 1);
        result = Math.min(result, attack(a - 1, b - 9, c - 3) + 1);
        result = Math.min(result, attack(a - 1, b - 3, c - 9) + 1);
        return memo[a][b][c] = result;
    }
}
