import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, String> map = new HashMap<>();
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			String domain = st.nextToken();
			String password = st.nextToken();
			map.put(domain, password);
		}
		
		while (M-- > 0) {
			String domain = br.readLine();
			bw.write(map.get(domain) + "\n");
		}
		
		bw.flush();
		br.close();
		bw.close();
		
	}
}