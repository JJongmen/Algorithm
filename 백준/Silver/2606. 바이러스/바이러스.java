import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static List<List<Integer>> graph;
	static boolean[] visit;
	static int virusCnt = 0;

	static void dfs(int node) {
		if (visit[node]) {
			return;
		} else {
			visit[node] = true;
			for (int i = 0; i < graph.get(node).size(); i++) {
				int next = graph.get(node).get(i);
				if (!visit[next]) {
					dfs(next);
					virusCnt++;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int linkCnt = Integer.parseInt(br.readLine());
		
		visit = new boolean[n+1];
		graph = new LinkedList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new LinkedList<Integer>());
		}
		
		for (int i = 0; i < linkCnt; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int com = Integer.parseInt(st.nextToken());
			int otherCom = Integer.parseInt(st.nextToken());
			graph.get(com).add(otherCom);
			graph.get(otherCom).add(com);
		}
		
		dfs(1);
		System.out.println(virusCnt);

	}
}