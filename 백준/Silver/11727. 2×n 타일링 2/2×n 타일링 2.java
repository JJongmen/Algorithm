import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] caseCnt = new int[1001];
		caseCnt[1] = 1;
		caseCnt[2] = 3;
		for (int i = 3; i <= n; i++) {
			caseCnt[i] = (caseCnt[i - 2] * 2 + caseCnt[i - 1]) % 10007;
		}
		System.out.println(caseCnt[n]);
	}
}