import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	
	static int gcd(int a, int b) {
		if (b == 0) {
			return a;
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

		Arrays.sort(paper);
		int gcdVal = paper[1] - paper[0];

		for (int i = 2; i < N; i++) {
			gcdVal = gcd(gcdVal, paper[i] - paper[i - 1]);
		}

		List<Integer> result = new ArrayList<>();
		for (int i = 2; i * i <= gcdVal; i++) {
			if (gcdVal % i != 0) continue;
			result.add(i);
			if (i * i != gcdVal) {
				result.add(gcdVal / i);
			}
		}
		Collections.sort(result);

		StringBuilder sb = new StringBuilder();
		for (Integer val : result) {
			sb.append(val).append(" ");
		}
		sb.append(gcdVal);
		System.out.println(sb);
	}
}