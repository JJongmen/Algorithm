import java.util.Scanner;

public class Main {

    private static int[][] memo = new int[19][19];

    private static int comb(int n, int r) {
        if (memo[n][r] != 0) {
            return memo[n][r];
        }
        if (r == 0 || r == n) {
            return memo[n][r] = 1;
        }
        return comb(n - 1, r) + comb(n - 1, r - 1);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double aScorePercentage = scan.nextInt() / 100.0;
        double bScorePercentage = scan.nextInt() / 100.0;

        int[] nonPrimeNums = {0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18};
        double aNonPrimePercentage = 0;
        double bNonPrimePercentage = 0;
        for (int i = 0; i < nonPrimeNums.length; i++) {
            int r = nonPrimeNums[i];
            aNonPrimePercentage += comb(18, r) * Math.pow(aScorePercentage, r) * Math.pow(1 - aScorePercentage, 18 - r);
            bNonPrimePercentage += comb(18, r) * Math.pow(bScorePercentage, r) * Math.pow(1 - bScorePercentage, 18 - r);
        }

        System.out.println(1 - aNonPrimePercentage * bNonPrimePercentage);
    }
}