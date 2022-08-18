import java.util.Scanner;

public class Main {
	static int[] board;
	static int N;
	static boolean[] rowCheck;		// - check
	static boolean[] lDiagCheck;	// \ check
	static boolean[] rDiagCheck;	// / check
	static int cnt = 0;
	
	static void set(int idx) {
		if (idx == N) {
			cnt++;
			return;
		}
		for (int i = 0; i < N; i++) {
			if(!rowCheck[i] && !lDiagCheck[N - i + idx] 
					&& !rDiagCheck[idx + i]) {
				board[idx] = i;
				rowCheck[i] = true;
				lDiagCheck[N - i + idx] = true;
				rDiagCheck[idx + i] = true;
				set(idx + 1);
				rowCheck[i] = false;
				lDiagCheck[N - i + idx] = false;
				rDiagCheck[idx + i] = false;
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		board = new int[N];
		rowCheck = new boolean[N];
		lDiagCheck = new boolean[2 * N + 1];
		rDiagCheck = new boolean[2 * N + 1];
		set(0);
		
		System.out.println(cnt);

	}
}