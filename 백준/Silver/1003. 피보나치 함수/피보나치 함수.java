import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[] zeroCnt = new int[41];
	static int[] oneCnt = new int[41];
	static {
		zeroCnt[0] = 1;
		oneCnt[1] = 1;
	}
	
	static void fib(int n) {
		for (int i = 2; i <= n; i++) {
			zeroCnt[i] = zeroCnt[i - 1] + zeroCnt[i - 2];
			oneCnt[i] = oneCnt[i - 1] + oneCnt[i - 2];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		fib(40);
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			bw.write(zeroCnt[N] + " " + oneCnt[N] + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}
}