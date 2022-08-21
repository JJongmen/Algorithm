import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static List<List<City>> graph = new ArrayList<>();
	static int[] minCosts;
	static final int INF = 1000000000;
	
	static class City {
		int des;	//destination
		int cost;
		
		City(int des, int cost) {
			this.des = des;
			this.cost = cost;
		}
	}
	
	static void getMinCost(int start) {
		Queue<City> que = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		que.add(new City(start, 0));
		while (!que.isEmpty()) {
			City road = que.poll();
			if (road.cost > minCosts[road.des]) { 
				continue;
			}
			for (City next : graph.get(road.des)) {
				int nextCost = road.cost + next.cost;
				if (nextCost < minCosts[next.des]) {
					minCosts[next.des] = nextCost; 
					que.offer(new City(next.des, nextCost));
				}
			}
		}
	}
	
	public static void makeMinCostTable(int N) {
		minCosts = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			minCosts[i] = INF;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i <= N; i++) {
			graph.add(new LinkedList<>());
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new City(b, c));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()); 
		int end = Integer.parseInt(st.nextToken()); 
		
		for (int i = 0; i <= N; i++) {
			graph.add(new LinkedList<City>());
		}
		
		makeMinCostTable(N);
		getMinCost(start);
		System.out.println(minCosts[end]);
	}
}