import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			String p = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String numsStr = br.readLine();
			boolean reverse = false;
			int i;
			
			StringTokenizer st = new StringTokenizer(numsStr.substring(1,numsStr.length()-1), ",");
			Deque<Integer> deque = new LinkedList<>();
			while (n-- > 0) {
				deque.offerLast(Integer.parseInt(st.nextToken()));
			}
			
			for (i = 0; i < p.length(); i++) {
				if (p.charAt(i) == 'R') {
					reverse = !reverse;
				} else if (p.charAt(i) == 'D') {
					if (deque.isEmpty()) {
						break;
					} else {
						if (reverse) {
							deque.pollLast();
						} else {
							deque.pollFirst();
						}
					}
				}
			}
			
			if (i == p.length()) {
				bw.write("[");
				if (reverse) {
					while (!deque.isEmpty()) {
						if (deque.size() == 1) {
							bw.write(deque.pollLast() + "");
						} else {
							bw.write(deque.pollLast() + ",");
						}
					}
				} else {
					while (!deque.isEmpty()) {
						if (deque.size() == 1) {
							bw.write(deque.pollFirst() + "");
						} else {
							bw.write(deque.pollFirst() + ",");
						}
					}
				}
				bw.write("]\n");
			} else {
				bw.write("error\n");
			}
			bw.flush();
	}
		br.close();
		bw.close();
	}
}