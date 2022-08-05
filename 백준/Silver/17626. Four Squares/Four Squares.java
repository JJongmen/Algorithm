import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] minCnt = new int[n + 1];
		
		int[] squares = new int[225];
		for (int i = 1; i <= 224; i++) {
			squares[i] = i * i;
		}

		int idx = 2;
		for (int i = 1; i <= n; i++) {
			while (i >= squares[idx]) {
				idx++;
			}
			minCnt[i] = 4;
			for (int j = idx - 1; j >= 1; j--) {
				minCnt[i] = Math.min(minCnt[i], 1 + minCnt[i - squares[j]]);
			}
		}
		System.out.println(minCnt[n]);
	}
}