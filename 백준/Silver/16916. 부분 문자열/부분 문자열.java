import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[] makeTable(String pattern) {
		int len = pattern.length();
		int table[] = new int[len];
		int idx = 0;
		for (int i = 1; i < len; i++) {
			while (idx > 0 && pattern.charAt(idx) != pattern.charAt(i)) {
				idx = table[idx - 1];
			} if (pattern.charAt(idx) == pattern.charAt(i)) {
				idx++;
				table[i] = idx;
			}
		}
		return table;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String P = br.readLine();
		int[] pi = makeTable(P);
		
		int idx = 0;
		int answer = 0;
		for (int i = 0; i < S.length(); i++) {
			while (idx > 0 && P.charAt(idx) != S.charAt(i)) {
				idx = pi[idx - 1];
			} if (P.charAt(idx) == S.charAt(i)) {
				idx++;
				if (idx == P.length()) {
					answer = 1;
					break;
				}
			}
		}
		System.out.println(answer);
	}
}