import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] trees = new int[N];
		int max = 0;
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			trees[i] = height;
			max = Math.max(max, height);
		}
		
		int low = 1;
		int high = max;
		while (low < high) {
			int mid = (int)(((long)low + high) / 2);
			long sum = 0;
			for (int i = 0; i < N; i++) {
				sum += trees[i] - mid > 0 ? trees[i] - mid : 0;
			}
			if (sum >= M) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		
		System.out.println(low - 1);
	}
}