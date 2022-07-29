import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class Main {
	
	static boolean checkLeftFast(int num, Deque<Integer> deque) {
		int cnt = 0;
		for (int i : deque) {
			if (i == num) {
				return true;
			} 
			if (cnt++ == deque.size() / 2) {
				break;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Deque<Integer> deque = new LinkedList<>();
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			deque.offerLast(i);
		}
		st = new StringTokenizer(br.readLine());
		while (M-- > 0) {
			int num = Integer.parseInt(st.nextToken());
			if(checkLeftFast(num, deque)) {
				while (deque.peekFirst() != num) {
					deque.offerLast(deque.pollFirst());
					cnt++;
				}
			} else {
				while (deque.peekFirst() != num) {
					deque.offerFirst(deque.pollLast());
					cnt++;
				}
			}
			deque.pollFirst();
		}
		System.out.println(cnt);
	}
}