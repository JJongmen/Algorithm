import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int[][] sticker = new int[2][n];
			int[][] dp = new int[2][n];
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			for (int i = 0; i < n; i++) {
				if (i >= 2) {
					dp[0][i] = Math.max(dp[1][i - 1],
							Math.max(dp[0][i - 2], dp[1][i - 2]))
							+ sticker[0][i];
					dp[1][i] = Math.max(dp[0][i - 1],
							Math.max(dp[0][i - 2], dp[1][i - 2]))
							+ sticker[1][i];
				} else if (i == 0) {
					dp[0][i] = sticker[0][i];
					dp[1][i] = sticker[1][i];
				} else if (i == 1) {
					dp[0][i] = dp[1][i - 1] + sticker[0][i];
					dp[1][i] = dp[0][i - 1] + sticker[1][i];
				}
			}

			bw.write(Math.max(dp[0][n - 1], dp[1][n - 1]) + "\n");
		}
		
		bw.flush();
		br.close();
		bw.close();

	}

}
