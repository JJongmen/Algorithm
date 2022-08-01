import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		long[] memo = new long[101];
		memo[1] = memo[2] = memo[3] = 1;
		for (int i = 4; i <= 100; i++) {
			memo[i] = memo[i - 3] + memo[i - 2];
		}
		
		
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			bw.write(memo[N] + "\n");
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
}