import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static final int INF = 100001;
	static int[] minTimes = new int[100001];
	static {
		for (int i = 0; i <= 100000; i++) {
			minTimes[i] = INF;
		}
	}
	
	static class Vertex {
		int v;	// vertex
		int t;	// time
		
		Vertex(int v, int t) {
			this.v = v;
			this.t = t;
		}
	}
	
	static boolean check(int v) {
		return 0 <= v && v < INF;
	}
	
	static void move(int start, int end) {
		Queue<Vertex> que = new LinkedList<>();
		que.offer(new Vertex(start, 0));
		minTimes[start] = 0;
		while (!que.isEmpty()) {
			int current = que.peek().v;
			int time = que.poll().t;
			if (current == end) {
				break;
			}
			if (time > minTimes[current]) {
				continue;
			}
			if (check(current - 1) && time + 1 < minTimes[current - 1]) {
				que.offer(new Vertex(current - 1, time + 1));
				minTimes[current - 1] = time + 1;
			}
			if (check(current + 1) && time + 1 < minTimes[current + 1]) {
				que.offer(new Vertex(current + 1, time + 1));
				minTimes[current + 1] = time + 1;
			}
			if (check(current * 2) && time < minTimes[current * 2]) {
				que.offer(new Vertex(current * 2, time));
				minTimes[current * 2] = time;
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		move(N, M);
		System.out.println(minTimes[M]);
	}
}