import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Map<Integer, Integer> map = new TreeMap<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			map.put(A, B);
		}
		
		List<Integer> list = new ArrayList<>(map.values());
		int[] dp = new int[N];
		int max = 0;
		for (int i = 0; i < N; i++) {
			int maxDp = 0;
			for (int j = 0; j < i; j++) {
				if (list.get(j) < list.get(i)) {
					maxDp = Math.max(maxDp, dp[j]);
				}
			}
			dp[i] = maxDp + 1;
			max = Math.max(max, dp[i]);
		}
		System.out.println(N - max);
	}
}