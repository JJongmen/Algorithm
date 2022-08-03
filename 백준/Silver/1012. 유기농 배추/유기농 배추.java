import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[][] field;
	static int M;
	static int N;
	
	static boolean checkInField(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M) {
			return true;
		} else {
			return false;
		}
	}
	
	static void dfs(int x, int y) {
		if (!checkInField(x, y) || checkInField(x, y) && field[x][y] != 1) {
			return;
		} else {
			field[x][y] = 0;
			dfs(x + 1, y);
			dfs(x - 1, y);
			dfs(x, y - 1);
			dfs(x, y + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			field = new int[N][M];
			for (int i = 0 ; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				field[Y][X] = 1;
			}
			
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (field[i][j] == 1) {
						dfs(i, j);
						cnt++;
					}
				}
			}
			
			bw.write(cnt + "\n");
		}
		
		bw.flush();
		br.close();
		bw.close();

	}
}