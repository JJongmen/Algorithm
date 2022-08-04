import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static boolean[] set = new boolean[21];
	static boolean[] emptySet = new boolean[21];
	static boolean[] allSet = new boolean[21];
	static BufferedWriter bw;
	
	static {
		for (int i = 1; i <= 20; i++) {
			allSet[i] = true;
		}
	}
	
	static void add(int x) {
		set[x] = true;
	}
	
	static void remove(int x) {
		set[x] = false;
	}
	
	static void check(int x) throws IOException {
		bw.write((set[x] ? 1 : 0) + "\n");
	}
	
	static void toggle(int x) {
		set[x] = !set[x];
	}
	
	static void all() {
		set = allSet.clone();
	}
	
	static void empty() {
		set = emptySet.clone();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String expr = st.nextToken();
			int num = 0;
			if (st.hasMoreTokens()) {
				num = Integer.parseInt(st.nextToken());
			}
			
			switch (expr) {
				case "add" : 
					add(num);
					break;
					
				case "remove" :
					remove(num);
					break;
				
				case "check" :
					check(num);
					break;
					
				case "toggle" :
					toggle(num);
					break;
					
				case "all" :
					all();
					break;
					
				case "empty" :
					empty();
					break;
			}
		}
		
		bw.flush();
		br.close();
		bw.close();

	}
}