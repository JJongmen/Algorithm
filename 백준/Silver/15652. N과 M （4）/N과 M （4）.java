import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static void recur(String comb) throws IOException {
		if (comb.length() == M * 2) {
			bw.write(comb + "\n");
			return;
		}
		char last = '1';
		int len = comb.length();
		if (len > 0) {
			last = comb.charAt(len - 2);
		}
		for (int i = last - '0'; i <= N; i++) {
			recur(comb + i + " ");
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	    
		recur("");
		
	    bw.flush();
	    br.close();
	    bw.close();
	}
}