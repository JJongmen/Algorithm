import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] nums;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static Set<String> set = new LinkedHashSet<>();
	
	static void recur(String comb, int last) throws IOException {
		if (comb.length() == M) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < M; i++) {
				sb.append(nums[comb.charAt(i) - '0'] + " ");
			}
			set.add(sb.toString());
		} else {
			for (int i = last; i < N; i++) {
				recur(comb + i, i);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
	
		recur("", 0);
		
		for (String seq : set) {
			bw.write(seq + "\n");
		}
	    bw.flush();
	    br.close();
	    bw.close();
	}
}