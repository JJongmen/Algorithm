import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Map<Integer, Integer> map = new HashMap<>();
	static int[] visit = new int[101];
	
	static void bfs(int start) {
		visit[start] = 1;
		Queue<Integer> que = new LinkedList<>();
		que.offer(start);
		while (!que.isEmpty()) {
			int pos = que.poll();
			if (pos == 100) {
				return;
			}
			for (int i = 1; i <= 6; i++) {
				int next = pos + i;
				next = map.getOrDefault(next, next);
				if (next <= 100 && visit[next] == 0) {
					visit[next] = visit[pos] + 1;
					que.offer(next);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			map.put(u, v);
		}
		
		bfs(1);
		System.out.println(visit[100] - 1);
	}
}