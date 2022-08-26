import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] prices = new int[n];
		int[] memo = new int[k + 1];
		for (int i = 0; i < n; i++) {
			prices[i] = Integer.parseInt(br.readLine());
		}
		
		memo[0] = 1;
		for (int i = 0; i < n; i++) {
			int price = prices[i];
			for (int j = 1; j <= k; j++) {
				if (i == 0 && j % price == 0) {
					memo[j] = 1;
				} 
				else if (i != 0) {
					if (j >= price) {
						memo[j] += memo[j - price];
					}
				}
			}
		}
		
		System.out.println(memo[k]);

	}
}