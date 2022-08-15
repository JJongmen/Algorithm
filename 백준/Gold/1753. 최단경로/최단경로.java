import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 200001;
	static int[] minDist;
	static List<Map<Integer, Integer>> graph;
	
	static class Edge {
		int v;
		int w;
		
		Edge (int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	
	static void dijkstra(int start) {
		minDist[start] = 0;
		Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		pq.offer(new Edge(start, 0));
		while (!pq.isEmpty()) {
			int current = pq.peek().v;
			int weight = pq.poll().w;
			if (minDist[current] < weight) {
				continue;
			}
			for (int next : graph.get(current).keySet()) {
				int nextWeight = weight + graph.get(current).get(next);
				if (nextWeight < minDist[next]) {
					minDist[next] = nextWeight;
					pq.offer(new Edge(next, nextWeight));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		minDist = new int[V + 1];
		graph = new ArrayList<>(V + 1);
		
		for (int i = 0; i < V + 1; i++) {
			minDist[i] = INF;
			graph.add(new HashMap<>());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			if (w < graph.get(u).getOrDefault(v, INF)) {
				graph.get(u).put(v, w);
			}
		}
		
		dijkstra(K);
		for (int i = 1; i <= V; i++) {
			if (minDist[i] == INF) {
				bw.write("INF\n");
			} else {
				bw.write(minDist[i] + "\n");
			}
		}
		
		bw.flush();
		br.close();
		bw.close();
		
	}
}