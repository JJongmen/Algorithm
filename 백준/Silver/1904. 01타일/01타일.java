import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] totalCnt = new int[N+1];
		totalCnt[0] = 1;
		totalCnt[1] = 1;
		for (int i = 2; i <= N; i++) {
			totalCnt[i] = (totalCnt[i - 2] + totalCnt[i - 1]) % 15746;
		}
		System.out.println(totalCnt[N]);

	}
}