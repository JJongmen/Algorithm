import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<LinkedList<Integer>> graph = new ArrayList<>();
	static boolean[] visit;
	static int[] parent;
	
	static void dfs(int start) {
		visit[start] = true;
		for (int i = 0; i < graph.get(start).size(); i++) {
			int next = graph.get(start).get(i);
			if (!visit[next]) {
				parent[next] = start;
				dfs(next);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i <= N; i++) {
			graph.add(new LinkedList<>());
		}
		parent = new int[N + 1];
		visit = new boolean[N + 1];
		
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			graph.get(node1).add(node2);
			graph.get(node2).add(node1);
		}
		
		dfs(1);
		
		for (int i = 2; i <= N; i++) {
			bw.write(parent[i] + "\n");
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
}