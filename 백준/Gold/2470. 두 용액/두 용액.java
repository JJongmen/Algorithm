import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] values = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			values[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(values);
		
		int left = 0;
		int right = N - 1;
		int min = 2000000000;
		int minLeft = left;
		int minRight = right;
		while (left < right) {
			int sum = values[left] + values[right];
			if (Math.abs(sum) < min) {
				min = Math.abs(sum);
				minLeft = left;
				minRight = right;
			}
			if (sum == 0) {
				break;
			} else if (sum < 0) {
				left++;
			} else if (sum > 0) {
				right--;
			}
		}
		
		System.out.println(values[minLeft] + " " + values[minRight]);
	}
}