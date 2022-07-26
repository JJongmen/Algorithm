import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] minRGB = new int[N+1][3];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			minRGB[i][0] = Math.min(minRGB[i-1][1], minRGB[i-1][2]) + Integer.parseInt(st.nextToken());
			minRGB[i][1] = Math.min(minRGB[i-1][0], minRGB[i-1][2]) + Integer.parseInt(st.nextToken());
			minRGB[i][2] = Math.min(minRGB[i-1][0], minRGB[i-1][1]) + Integer.parseInt(st.nextToken());
		}
		System.out.println(Math.min(Math.min(minRGB[N][0], minRGB[N][1]), minRGB[N][2]));
	}

}