import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static List<List<Integer>> graph;
	static int[] visit;
	
	static void dfs(int n) {
		for (int i = 0; i < graph.get(n).size(); i++) {
			int next = graph.get(n).get(i);
			if (visit[next] == 0) {
				visit[next] = 1;
				dfs(next);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());

		
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int j = 0;
			while (st.hasMoreElements()) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					graph.get(i).add(j);
				}
				j++;
			}
		}
		
		for (int i = 0; i < N; i++) {
			visit = new int[N];
			dfs(i);
			for (int j = 0; j < N; j++) {
				bw.write(visit[j] + " ");
			}
			bw.write("\n");
		}
		
		bw.flush();
		br.close();
		bw.close();
		
	}
}