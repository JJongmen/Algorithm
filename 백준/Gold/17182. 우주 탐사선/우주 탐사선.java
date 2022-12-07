import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] d = new int[10][10];
    static int[] pattern = new int[10];
    static boolean[] isUsed = new boolean[10];
    static int result = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                d[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        pattern[0] = K;
        isUsed[K] = true;
        calculateMinTime(1);

        System.out.println(result);
    }

    private static void calculateMinTime(int order) {
        if (order == N) {
            int time = 0;
            for (int i = 0; i < N - 1; i++) {
                int before = pattern[i];
                int after = pattern[i + 1];
                time += d[before][after];
            }
            result = Math.min(result, time);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (isUsed[i]) continue;
            pattern[order] = i;
            isUsed[i] = true;
            calculateMinTime(order + 1);
            isUsed[i] = false;
        }
    }
}