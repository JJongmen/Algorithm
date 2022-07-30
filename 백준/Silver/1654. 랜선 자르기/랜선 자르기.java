import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] lanLines = new int[K];
		long max = 0;
		for (int i = 0; i < K; i++) {
			lanLines[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, lanLines[i]);
		}
		long low = 1;
		long high = max + 1;
		while (low < high) {
			long mid = (low + high) / 2;

			long tmp = 0;
			for (int i = 0; i < K; i++) {
				tmp += lanLines[i] / mid;
			}
			if (tmp >= N) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		System.out.println(low - 1);
	}
}