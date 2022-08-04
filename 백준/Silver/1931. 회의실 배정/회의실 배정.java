import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class Time {
		int start;
		int end;
		
		Time(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Time[] meetings = new Time[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			meetings[i] = new Time(start, end);
		}
		
		Arrays.sort(meetings,
				(o1, o2) -> o1.end - o2.end != 0 ?
						o1.end - o2.end : o1.start - o2.start);

		int end = meetings[0].end;
		int cnt = 1;
		for (int i = 1; i < N; i++) {
			int start = meetings[i].start;
			if (end <= start) {
				end = meetings[i].end;
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}