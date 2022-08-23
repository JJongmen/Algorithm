import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 북, 동 , 남 , 서
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	static int[][] area;
	
	static int r;
	static int c;
	static int d;
	static boolean powerFlag = true;
	
	static void clean() {
		area[r][c] = 2;
	}
	
	static boolean moveAndTurn() {
		int leftDirection = (d + 3) % 4;
		int behindDirection = (d + 2) % 4;
		boolean leftCleanPossible = true;
		boolean behindWall = false;
		int i = leftDirection;
		int cnt = 0;
		while (cnt < 4) {
			if (area[r + dx[i]][c + dy[i]] == 0) {
				break;
			}
			if (i == leftDirection && area[r + dx[i]][c + dy[i]] != 0) {
				leftCleanPossible = false;
			}
			if (i == behindDirection && area[r + dx[i]][c + dy[i]] == 1) {
				behindWall = true;
			}
			if (cnt == 3) {
				// 2-4의 경우 : 작동을 멈춘다
				if (behindWall) {
					powerFlag = false;
					return false;
				}
				// 2-3의 경우 : 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
				r += dx[behindDirection];
				c += dy[behindDirection];
				return true;
			}
			i = (i + 3) % 4;
			cnt++;
		}
		// 2-1 의 경우 : 그 방향으로 회전하고 한 칸 전진하고 1번부터 진행
		if (leftCleanPossible) {
			d = leftDirection;
			r += dx[leftDirection];
			c += dy[leftDirection];
			return false;
		}
		// 2-2의 경우 : 그 방향으로 회전하고 2번으로 돌아간다.
		d = leftDirection;
		return true;
	}
	
	static int cleanAndMove() {
		int cnt = 0;
		
		while (powerFlag) {
			if (area[r][c] == 0) {
				clean();
				cnt++;
			}
			while (moveAndTurn()) {}
		}
		
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 세로 크기 N, 가로 크기 M
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		area = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		// 로봇 청소기가 있는 칸의 좌표 (r,c)
		r = Integer.parseInt(st.nextToken());	
		c = Integer.parseInt(st.nextToken());
		// 바라보는 방향 (북, 동 , 남 , 서) = (0, 1, 2, 3)
		d = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(cleanAndMove());

	}
}