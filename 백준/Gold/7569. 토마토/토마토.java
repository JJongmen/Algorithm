import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M;
	static int N;
	static int H;
	static int[] dx = {0, 0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, 0, -1, 1};
	static int[] dh = {-1, 1, 0, 0, 0, 0};
	static int[][][] storage;
	static Queue<Tomato> tomatos = new LinkedList<>(); 
	
	static class Tomato {
		int x;
		int y;
		int h;
		
		Tomato(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}
	}
	
	static boolean checkInStorage(int x, int y, int h) {
		return 0 <= x && x < M && 0 <= y && y < N && 0 <= h && h < H;
	}
	
	static void bfs() {
		while (!tomatos.isEmpty()) {
			Tomato tomato = tomatos.poll();
			int x = tomato.x;
			int y = tomato.y;
			int h = tomato.h;
			for (int i = 0; i < 6; i++) {
				int nextX = tomato.x + dx[i];
				int nextY = tomato.y + dy[i];
				int nextH = tomato.h + dh[i];
				if (checkInStorage(nextX, nextY, nextH) 
						&& storage[nextH][nextY][nextX] == 0) {
					storage[nextH][nextY][nextX] = storage[h][y][x] + 1;
					tomatos.offer(new Tomato(nextX, nextY, nextH));
				}
			}
		}
	}
	
	static int minRiped(int[][][] storage) {
		int max = 0;
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int day = storage[h][i][j];
					if (day == 0) {
						return -1;
					} else {
						max = Math.max(max, day);
					}
				}
			}
		}
		return max - 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		storage = new int[H][N][M];
		
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int status = Integer.parseInt(st.nextToken());
					if (status == 1) {
						tomatos.offer(new Tomato(j, i, h));
					}
					storage[h][i][j] = status;
				}
			}
		}
		
		bfs();
		System.out.println(minRiped(storage));
	}
}