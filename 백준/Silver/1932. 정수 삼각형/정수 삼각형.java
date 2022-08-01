import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] memo = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				memo[i][j] = Integer.parseInt(st.nextToken());
				if (j == 0 && i != 0) {
					memo[i][0] += memo[i-1][0];
				} else if (j == i && i != 0) {
					memo[i][i] += memo[i-1][i-1];
				} else if (0 < j && j < i && i != 0) {
					memo[i][j] += Math.max(memo[i-1][j-1], memo[i-1][j]);					
				}
			}
		}
		
		int max = 0;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, memo[n-1][i]);
		}
		System.out.println(max);	
	}
}