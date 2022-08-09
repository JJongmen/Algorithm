import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		BigInteger[][] memo = new BigInteger[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			memo[i][0] = memo[i][i] = new BigInteger("1");
			memo[i][1] = memo[i][i-1] = new BigInteger(i + "");
			for (int j = 2; j <= i/2; j++) {
				memo[i][j] = new BigInteger(memo[i-1][j-1] + "")
						.add(new BigInteger(memo[i-1][j] + ""));
				memo[i][i-j] = memo[i][j];
			}
		}

		System.out.println(memo[n][m]);

	}
}