import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>> graph;
	static int[] visitOrder;
	static int[] KBCnt;
	
	static void bfs(int n) {
		visitOrder[n] = 1;
		Queue<Integer> que = new LinkedList<>();
		que.add(n);
		
		while (!que.isEmpty()) {
			int current = que.poll();
			for (int i = 0; i < graph.get(current).size(); i++) {
				int next = graph.get(current).get(i);
				if (visitOrder[next] == 0) {
					visitOrder[next] = visitOrder[current] + 1;
					que.add(next);
				}
			}
		}
		
		int cnt = 0;
		for (int i = 1; i < visitOrder.length; i++) {
			cnt += visitOrder[i] - 1;
		}
		KBCnt[n] = cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		KBCnt = new int[N + 1];
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph.get(A).add(B);
			graph.get(B).add(A);
		}
		
		
		for (int i = 1; i <= N; i++) {
			visitOrder = new int[N + 1];
			bfs(i);
		}
		
		int minIdx = 1;
		for (int i = 1; i <= N; i++) {
			if (KBCnt[minIdx] > KBCnt[i]) {
				minIdx = i;
			}
		}
		
		System.out.println(minIdx);

	}
}