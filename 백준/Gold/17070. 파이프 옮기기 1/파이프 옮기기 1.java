import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static final int HORIZONTAL = 0;	// 가로
	static final int VERTICAL = 1;		// 세로
	static final int DIAGONAL = 2;		// 대각선
	static int[][] house;
	static int N;
	static int cnt = 0;
	
	static boolean canMove(int afterState, int x, int y) {
		if (afterState == HORIZONTAL) {
			if (y + 1 < N && house[x][y + 1] != 1) {
				return true;
			} else {
				return false;
			}
		} else if (afterState == VERTICAL) {
			if (x + 1 < N && house[x + 1][y] != 1) {
				return true;
			} else {
				return false;
			}
		} else if (afterState == DIAGONAL) {
			if (x + 1 < N && y + 1 < N && house[x][y + 1] != 1 
				&& house[x + 1][y] != 1 && house[x + 1][y + 1] != 1) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	static void move(int state, int x, int y) {
		if (x == N - 1 && y == N - 1) {
			cnt++;
			return;
		}
		
		if (state == HORIZONTAL) {
			if (canMove(HORIZONTAL, x, y)) {
				move(HORIZONTAL, x, y + 1);
			}
			if (canMove(DIAGONAL, x, y)) {
				move(DIAGONAL, x + 1, y + 1);
			}
		} else if (state == VERTICAL) {
			if (canMove(VERTICAL, x, y)) {
				move(VERTICAL, x + 1, y);
			}
			if (canMove(DIAGONAL, x, y)) {
				move(DIAGONAL, x + 1, y + 1);
			}
		} else if (state == DIAGONAL) {
			if (canMove(HORIZONTAL, x, y)) {
				move(HORIZONTAL, x, y + 1);
			}
			if (canMove(VERTICAL, x, y)) {
				move(VERTICAL, x + 1, y);
			}
			if (canMove(DIAGONAL, x, y)) {
				move(DIAGONAL, x + 1, y + 1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		house = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		move(HORIZONTAL, 0, 1);
		System.out.println(cnt);

	}
}