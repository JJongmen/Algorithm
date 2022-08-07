import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		String S = br.readLine();
		
		String pattern = "IO".repeat(N) + "I";
		int[] pi = new int[2*N + 1];
		for (int i = 2; i < 2*N + 1; i++) {
			pi[i] = i - 1;
		}
		
		int cnt = 0;
		int idx = 0;
		for (int i = 0; i < M; i++) {
			while (idx > 0 && pattern.charAt(idx) != S.charAt(i)) {
				idx = pi[idx - 1];
			}
			if (pattern.charAt(idx) == S.charAt(i)) {
				if (idx == 2 * N) {
					cnt++;
					idx = pi[idx];
				} else {
					idx += 1;
				}

			}
			
		}
		System.out.println(cnt);
	}
}