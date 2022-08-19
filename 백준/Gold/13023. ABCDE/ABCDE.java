import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static List<List<Integer>> graph;
	static boolean[] visit;
	static final int EXIST = 1;
	static final int NOT_EXIST = 0;
	static int answer = NOT_EXIST;
	
	static void dfs(int start, int cnt) {
		if (cnt == 5) {
			answer = EXIST;
			return;
		}
		visit[start] = true;
		for (int next : graph.get(start)) {
			if (!visit[next]) {
				dfs(next, cnt + 1);
				visit[next] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			graph.add(new LinkedList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		int i = 0;
		while (answer == NOT_EXIST && i < N) {
			visit = new boolean[N];
			dfs(i, 1);
			i++;
		}
		System.out.println(answer);
	}
}