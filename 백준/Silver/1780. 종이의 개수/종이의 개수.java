import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int minusCnt = 0;
	static int zeroCnt = 0;
	static int plusCnt = 0;
	static int[][] paper;
	static int[] dx = {0, 0, 0, 1, 1, 1, 2, 2, 2};
	static int[] dy = {0, 1, 2, 0, 1, 2, 0, 1, 2};
	
	static boolean isSame(int size, int x_idx, int y_idx) {
		for(int i = x_idx; i < x_idx + size; i++) {
			for(int j = y_idx; j < y_idx + size; j++) {
				if(paper[i][j] != paper[x_idx][y_idx])
					return false;
			}
		}
		return true;
	}
	
	static void cut(int size, int x_idx, int y_idx) {
		if(isSame(size, x_idx, y_idx) || size == 1) {
			if(paper[x_idx][y_idx] == -1)
				minusCnt++;
			else if(paper[x_idx][y_idx] == 0) 
				zeroCnt++;
			else if(paper[x_idx][y_idx] == 1) 
				plusCnt++;
			return;
		}
		else {
			size /= 3;
			for(int i = 0; i < 9; i++) 
				cut(size, x_idx + dx[i] * size, y_idx + dy[i] * size);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) 
				paper[i][j] = Integer.parseInt(st.nextToken());
		}
		cut(N, 0, 0);
		System.out.println(minusCnt);
		System.out.println(zeroCnt);
		System.out.println(plusCnt);
	}
}