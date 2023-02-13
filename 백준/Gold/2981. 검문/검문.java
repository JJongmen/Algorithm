import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int gcd(int a, int b) {
		if (a % b == 0) {
			return b;
		}
		return gcd(b, a % b);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] paper = new int[N];
		for (int i = 0; i < N; i++) {
			paper[i] = Integer.parseInt(br.readLine());
		}

		int gcdTmp = Math.abs(paper[1] - paper[0]);
		for (int i = 2; i < N; i++) {
			gcdTmp = gcd(gcdTmp, Math.abs(paper[i] - paper[i - 1]));
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= gcdTmp; i++) {
			if (gcdTmp % i != 0) continue;
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
}