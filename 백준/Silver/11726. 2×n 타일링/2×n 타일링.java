import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] memo = new int[1001];
		memo[1] = 1;
		memo[2] = 2;
		for (int i = 3; i <= n; i++) {
			memo[i] = (memo[i - 2] + memo[i - 1]) % 10007;
		}
		System.out.println(memo[n]);

	}
}