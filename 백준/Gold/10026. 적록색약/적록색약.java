import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String[] grid;
	static boolean[][] visit;
	static int N;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static boolean check(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
	
	static void dfs(int x, int y, char color) {
		visit[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (check(nextX, nextY) && !visit[nextX][nextY] 
					&& grid[nextX].charAt(nextY) == color) {
				dfs(nextX, nextY, color);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		grid = new String[N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			grid[i] = br.readLine();
		}
		
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					dfs(i, j, grid[i].charAt(j));
					cnt++;
				}
			}
		}
		
		int colorWeeknessCnt = 0;
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			grid[i] = grid[i].replace('G', 'R');
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					dfs(i, j, grid[i].charAt(j));
					colorWeeknessCnt++;
				}
			}
		}
		
		System.out.println(cnt + " " + colorWeeknessCnt);
		
	}
}