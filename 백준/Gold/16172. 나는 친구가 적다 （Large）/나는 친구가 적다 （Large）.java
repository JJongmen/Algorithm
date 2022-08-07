import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[] makeTable(String pattern) {
		int len = pattern.length();
		int idx = 0;
		int[] table = new int[len];
		for (int i = 1; i < len; i++) {
			while (idx > 0 && pattern.charAt(idx) != pattern.charAt(i)) {
				idx = table[idx - 1];
			} if (pattern.charAt(idx) == pattern.charAt(i)) {
				idx += 1;
				table[i] = idx;
			}
		}
		return table;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String K = br.readLine();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < S.length(); i++) {
			char ch = S.charAt(i);
			if (!('0' <= ch && ch <= '9')) {
				sb.append(ch);
			}
		}
		String original = sb.toString();
		
		int answer = 0;
		if (original.length() >= K.length()) {
			
			int idx = 0;
			int[] pi = makeTable(K);
			
			for (int i = 0; i < original.length(); i++) {
				while (idx > 0 && K.charAt(idx) != original.charAt(i)) {
					idx = pi[idx - 1];
				} if (K.charAt(idx) == original.charAt(i)) {
					idx += 1;
					if (idx == K.length()) {
						answer = 1;
						break;
					}
				}
			}
		}
		System.out.println(answer);
	}
}