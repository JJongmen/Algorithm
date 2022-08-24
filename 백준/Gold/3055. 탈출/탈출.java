import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static final int INF = 10000;
	static int R;
	static int C;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] waterVisit;
	static Position start;
	static Position end;
	static Queue<Position> waterQue = new LinkedList<>();
	
	static class Position {
		int x;
		int y;
		
		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static boolean check(int x, int y) {
		return 0 <= x && x < R && 0 <= y && y < C;
	}
	
	public static String solve() {
		makeWaterMap();
		moveHedghog();
		if (map[end.x][end.y] == INF) {
			return "KAKTUS";
		} else {
			return (map[end.x][end.y] - 1) + ""; 
		}
	}

	private static void moveHedghog() {
		map[start.x][start.y] = 1;
		boolean[][] visit = new boolean[R][C];
		visit[start.x][start.y] = true; 
		Queue<Position> que = new LinkedList<>();
		que.offer(start);
		while (!que.isEmpty()) {
			Position current = que.poll();
			if (current.equals(end)) {
				// 종료조건
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nextX = current.x + dx[i];
				int nextY = current.y + dy[i];
				if (check(nextX, nextY) && !visit[nextX][nextY] && map[nextX][nextY] != -1 
						&& ((waterVisit[nextX][nextY] && map[current.x][current.y] + 1 < map[nextX][nextY])
						|| !waterVisit[nextX][nextY])) {
					map[nextX][nextY] = map[current.x][current.y] + 1;
					visit[nextX][nextY] = true;
					que.offer(new Position(nextX, nextY));
				}
			}
			
		}
	}
	
	private static void makeWaterMap() {
		waterVisit = new boolean[R][C];
		while (!waterQue.isEmpty()) {
			Position water = waterQue.poll();
			for (int i = 0; i < 4; i++) {
				int nextX = water.x + dx[i];
				int nextY = water.y + dy[i];
				if (check(nextX, nextY) && map[nextX][nextY] == 0) {
					map[nextX][nextY] = map[water.x][water.y] + 1; 
					waterVisit[nextX][nextY] = true;
					waterQue.offer(new Position(nextX, nextY));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				char ch = str.charAt(j);
				if (ch == 'S') {
					start = new Position(i, j);
				} else if (ch == 'D') {
					end = new Position(i, j);
					map[i][j] = INF;
				} else if (ch == '*') {
					map[i][j] = 1;
					waterQue.offer(new Position(i, j));
				} else if (ch == 'X') {
					map[i][j] = -1;
				}
			}
		}
		
		
		System.out.println(solve());
		
	}
}