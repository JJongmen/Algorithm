import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "-");
		int sum = 0;
		boolean first = true;
		while (st.hasMoreElements()) {
			String expr = st.nextToken();
			StringTokenizer nums = new StringTokenizer(expr, "+");
			int num = 0;
			while (nums.hasMoreElements()) {
				num += Integer.parseInt(nums.nextToken());
			}
			if (first) {
				sum += num;
				first = false;
			} else {
				sum -= num;
			}
		}
		
		System.out.println(sum);

	}
}