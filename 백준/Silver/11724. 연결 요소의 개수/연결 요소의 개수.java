import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static List<List<Integer>> graph;
	static boolean[] visit;
	
	static void dfs(int node) {
		if (visit[node]) {
			return;
		} else {
			visit[node] = true;
			for (int i = 0; i < graph.get(node).size(); i++) {
				dfs(graph.get(node).get(i));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new LinkedList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new LinkedList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		visit = new boolean[N + 1];
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (!visit[i]) {
				dfs(i);
				cnt++;
			}
		}
		
		System.out.println(cnt);

	}
}