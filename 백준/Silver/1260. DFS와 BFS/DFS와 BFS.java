import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>> graph;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static boolean[] visit;
	
	static void dfs(int n) throws IOException {
		if(visit[n]) {
			return;
		} else {
			bw.write(n + " ");
			visit[n] = true;
			for (int i = 0; i < graph.get(n).size(); i++) {
				dfs(graph.get(n).get(i));
			}
		}
	}
	
	static void bfs(int n) throws IOException {
		Queue<Integer> que = new LinkedList<>();
		que.offer(n);
		visit[n] = true;
		bw.write(n + " ");
		
		while (!que.isEmpty()) {
			int current = que.poll();
			for (int i = 0; i < graph.get(current).size(); i++) {
				int next = graph.get(current).get(i);
				if (!visit[next]) {
					que.offer(next);
					visit[next] = true;
					bw.write(next + " ");
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		graph = new LinkedList<>();
		
		for (int i = 0; i <= N; i++) {
			graph.add(new LinkedList<>());
		}
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			graph.get(i).add(j);
			graph.get(j).add(i);
		}
		
		for (int i = 1; i <= N; i++) {
			graph.get(i).sort((o1, o2) -> o1.compareTo(o2));
		}
		
		visit = new boolean[N+1];
		dfs(V);
		bw.write("\n");
		visit = new boolean[N+1];		
		bfs(V);
		
		bw.flush();
		br.close();
		bw.close();
	}
}