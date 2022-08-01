import java.util.Scanner;

public class Main {
	static int recur_cnt = 0;
	static int dp_cnt = 0;
	static int dp[];
	
	static int recur_fib(int n) {
		if (n == 1 || n == 2) {
			recur_cnt++;
			return 1;
		} else {
			return (recur_fib(n - 1) + recur_fib(n - 2));
		}
	}
	
	static int dp_fib(int n) {
		dp[1] = dp[2] = 1;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
			dp_cnt++;
		}
		return dp[n];
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		dp = new int[n + 1];
		recur_fib(n);
		dp_fib(n);
		System.out.println(recur_cnt + " " + dp_cnt);
	}
}