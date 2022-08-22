import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int L;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static Set<Character> vowels = new HashSet<>();
	
	static {
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
	}
	
	static boolean checkPassword(String comb) {
		int vowelCnt = 0;
		for (int i = 0; i < comb.length(); i++) {
			if (vowels.contains(comb.charAt(i))) {
				vowelCnt++;
			}
		}
		return 1 <= vowelCnt && vowelCnt <= comb.length() - 2;
	}
	
	static void dfs(String comb, String others) throws IOException {
		if (comb.length() == L && checkPassword(comb)) {
			bw.write(comb + "\n");
			return;
		}
		for (int i = 0; i < others.length(); i++) {
			dfs(comb + others.charAt(i), others.substring(i + 1));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		char[] alphabets = new char[C];
		for (int i = 0; i < C; i++) {
			alphabets[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(alphabets);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < C; i++) {
			sb.append(alphabets[i]);
		}
		
		dfs("", sb.toString());
		
		bw.flush();
		br.close();
		bw.close();
		
	}
}