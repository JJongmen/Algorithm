import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[] graph;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		graph = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			int j = Integer.parseInt(br.readLine());
			graph[i] = j;
		}

		int size = 0;
		StringBuilder result = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			visit = new boolean[N + 1];
			if (hasCycle(i, i)) {
				size++;
				result.append(i).append("\n");
			}
		}

		System.out.println(size);
		System.out.println(result);
	}

	private static boolean hasCycle(int start, int node) {
		if (visit[node]) {
			return start == node;
		}

		visit[node] = true;
		int nxt = graph[node];
		return hasCycle(start, nxt);
	}
}