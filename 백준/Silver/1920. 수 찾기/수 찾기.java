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
		
		int N = Integer.parseInt(br.readLine());
		Set<Integer> set = new HashSet<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		while (N-- > 0) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		while (M-- > 0) {
			int num = Integer.parseInt(st.nextToken());
			if (set.contains(num)) {
				bw.write("1\n");
			} else {
				bw.write("0\n");
			}
		}
		
		bw.flush();
		br.close();
		bw.close();

	}
}