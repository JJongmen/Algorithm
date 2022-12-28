import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int N, M;
	static int[][] board = new int[300][300];
	static int[][] around = new int[300][300];
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int year = 0;
		while (true) {
			visit = new boolean[N][M];
			int glacier = 0;
			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < M - 1; j++) {
					if (board[i][j] <= 0) continue;
					if (visit[i][j]) continue;
					dfs(i, j);
					glacier++;
				}
			}
			if (glacier == 0) {
				System.out.println(0);
				return;
			}
			if (glacier > 1) {
				System.out.println(year);
				return;
			}

			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < M - 1; j++) {
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						if (board[i + dx[k]][j + dy[k]] > 0) continue;
						cnt++;
					}
					around[i][j] = cnt;
				}
			}

			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < M - 1; j++) {
					if (board[i][j] <= 0) continue;
					board[i][j] -= around[i][j];
				}
			}
			year++;
		}
	}

	private static void dfs(int sx, int sy) {
		visit[sx][sy] = true;
		for (int i = 0; i < 4; i++) {
			int nx = sx + dx[i];
			int ny = sy + dy[i];
			if (board[nx][ny] <= 0) continue;
			if (visit[nx][ny]) continue;
			dfs(nx, ny);
		}
	}
}