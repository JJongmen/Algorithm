import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long B;
    static int[][][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        int size = (int) (Math.log(B) / Math.log(2));
        memo = new int[size + 1][N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                memo[0][i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int l = 0; l < size; l++) {
            memo[l + 1] = matrixMul(memo[l], memo[l]);
        }

        char[] bin = Long.toBinaryString(B).toCharArray();
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            result[i][i] = 1;
        }

        for (int i = 0; i <= size; i++) {
            if (bin[bin.length - i - 1] == '0') continue;
            result = matrixMul(result, memo[i]);
        }

        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(" ");
            }
            System.out.println(sb);
        }
    }

    private static int[][] matrixMul(int[][] a, int[][] b) {
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    matrix[i][j] += a[i][k] * b[k][j];
                }
                matrix[i][j] %= 1000;
            }
        }
        return matrix;
    }
}