import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static final int MOD = 1_000_000_007;

    static long[][][] memo = new long[62][2][2];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long N = scan.nextLong();

        int maxBit = (int) (Math.log(N - 1) / Math.log(2));
        memo[0][0][0] = memo[0][0][1] = memo[0][1][0] = 1;

        for (int i = 0; i < maxBit; i++) {
            memo[i + 1] = multiple(memo[i], memo[i]);
        }

        long[][] tmp = {{1, 0}, {0, 1}};
        for (int i = 0; i <= maxBit; i++) {
            if (((N - 1) & (1L << i)) == 0) continue;
            tmp = multiple(tmp, memo[i]);
        }

        long[][] matrix = {{1}, {0}};
        tmp = multiple(tmp, matrix);
        System.out.println(tmp[0][0]);
    }

    private static long[][] multiple(long[][] a, long[][] b) {
        int rowA = a.length;
        int colA = a[0].length;
        int colB = b[0].length;
        long[][] matrix = new long[rowA][colB];
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colB; j++) {
                for (int k = 0; k < colA; k++) {
                    matrix[i][j] += a[i][k] * b[k][j];
                }
                matrix[i][j] %= MOD;
            }
        }
        return matrix;
    }
}