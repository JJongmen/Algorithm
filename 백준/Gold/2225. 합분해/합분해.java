import java.util.Scanner;

public class Main {
	
	static int[][] memo;
	
	/*
	static int recur(int n, int k) {
		if (k == 1) {
			return memo[n][k] = 1;
		}
		int sum = 0;
		for (int i = 0; i <= n; i++) {
			if (memo[i][k - 1] != 0) {
				sum += memo[i][k - 1];
			} else {
				sum += recur(i, k - 1) % 1000000000;
			}
			
		}
		return sum % 100000000; 
	}
	*/
	
	static void makeTable(int n, int k) {
		for (int i = 1; i < k; i++) {
			int sum = 0;
			for (int j = 0; j <= n; j++) {
				sum += memo[j][i] % 1000000000;
				memo[j][i+1] = sum %= 1000000000;
			}
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int K = scan.nextInt();
		
		memo = new int[N + 1][K + 1];
		for (int i = 0; i <= N; i++) {
			memo[i][1] = 1;
		}
		
		makeTable(N, K);
		System.out.println(memo[N][K]);

	}
}