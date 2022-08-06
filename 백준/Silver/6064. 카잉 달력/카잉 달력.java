import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			Set<Long> set = new HashSet<>(N);
			for (int i = 0; i < N; i++) {
				set.add(x + (long)M * i);
			}
			
			for (int i = 0; i < M; i++) {
				long num = y + (long)N * i;
				if (set.contains(num)) {
					bw.write(num + "\n");
					break;
				}
				if (i == M - 1) {
					bw.write("-1\n");
				}
			}
		}
		
		bw.flush();
		br.close();
		bw.close();

	}
}