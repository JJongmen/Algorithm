import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] nums;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static void recur(String comb, String others) throws IOException {
		if (comb.length() == M) {
			for (int i = 0; i < M; i++) {
				bw.write(nums[comb.charAt(i) - '0'] + " ");
			}
			bw.write("\n");
		} else {
			for (int i = 0; i < others.length(); i++) {
				recur(comb + others.charAt(i), others.substring(0, i) + others.substring(i + 1));
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
		
		StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < N; i++) {
	    	sb.append(i);
	    }
		recur("", sb.toString());
		
	    bw.flush();
	    br.close();
	    bw.close();
	}
}