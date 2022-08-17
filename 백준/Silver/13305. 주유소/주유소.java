import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 1000000001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] dist = new int[N - 1];
		int[] price = new int[N];
		long sum = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N - 1; i++) {
			dist[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		long currentMinPrice = INF;
		for (int i = 0; i < N - 1; i++) {
			if (price[i] < currentMinPrice) {
				currentMinPrice = price[i];
			}
			sum += currentMinPrice * dist[i];
		}
		
		System.out.println(sum);
	}
}