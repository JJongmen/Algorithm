import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] storage;
	static int M;
	static int N;
	static int zeroCnt = 0;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Queue<Point> que = new LinkedList<>();
	
	static boolean checkInStorage(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M) {
			return true;
		} else {
			return false;
		}
	}
	
	static void bfs() {
		while (!que.isEmpty()) {
			Point current = que.poll();
			int currentX = (int)current.getX();
			int currentY = (int)current.getY();
			for (int i = 0; i < 4; i++) {
				int nextX = currentX + dx[i];
				int nextY = currentY + dy[i];
				if (checkInStorage(nextX, nextY)) {
					if (storage[nextX][nextY] == 0) {
						storage[nextX][nextY] = storage[currentX][currentY] + 1;
						que.add(new Point(nextX, nextY));
						if (--zeroCnt == 0) {
							return;
						}
					}
				}
			}
		}
	}
	
	static int minPeriod() {
		if (zeroCnt > 0) {
			return -1;
		}
		int max = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				max = Math.max(max, storage[i][j]);
			}
		}
		return max == -1 ? -1 : max - 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		storage = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				storage[i][j] = Integer.parseInt(st.nextToken());
				if (storage[i][j] == 1) {
					Point tomato = new Point(i, j);
					que.add(tomato);
				} else if (storage[i][j] == 0) {
					zeroCnt++;
				}
			}
		}
		
		bfs();
		
		System.out.println(minPeriod());
	}
}