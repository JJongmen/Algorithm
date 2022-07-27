import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] paper;
	static final int BLUE = 1;
	static final int WHITE = 0;
	static int white_cnt = 0;
	static int blue_cnt = 0;
	
	static boolean isSameColor(int size, int x_idx, int y_idx) {
		for(int i = x_idx; i < x_idx + size; i++) {
			for(int j = y_idx; j < y_idx + size; j++) {
				if(paper[i][j] != paper[x_idx][y_idx])
					return false;
			}
		}
		return true;
	}
	
	static void cut(int size, int x_idx, int y_idx) {
		if(isSameColor(size, x_idx, y_idx) || size == 1) {
			if(paper[x_idx][y_idx] == BLUE)
				blue_cnt++;
			if(paper[x_idx][y_idx] == WHITE) {
				white_cnt++;
			}
			return;
		}
		else {
			cut(size/2, x_idx, y_idx);
			cut(size/2, x_idx + size/2, y_idx);
			cut(size/2, x_idx, y_idx + size/2);
			cut(size/2,  x_idx + size/2, y_idx + size/2);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) 
				paper[i][j] = Integer.parseInt(st.nextToken());
		}
		cut(N,0,0);
		System.out.println(white_cnt);
		System.out.println(blue_cnt);
	}
}