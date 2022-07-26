import java.util.Scanner;

public class Main {
	static final int MAX_NUM = 1_000_000_000;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[][] dp = new int[N][12];
		int cnt = 0;
		for(int i = 1; i <= 9; i++)
			dp[0][i] = 1;
		for(int i = 1; i < N; i++) {
			for(int j = 0; j <= 9; j++) 
				dp[i][j+1] = (dp[i-1][j] + dp[i-1][j+2]) % MAX_NUM;
		}
		for(int i = 1; i<= 10; i++) { 
			cnt += dp[N-1][i];
			cnt %= MAX_NUM;
		}
		System.out.println(cnt);

	}

}
