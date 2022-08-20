import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int L;
	static int R;
	static int C;
	static int[][][] building;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int[] dx = {0, 0, 0, 0, 1, -1};
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static int[] dz = {1, -1, 0, 0, 0, 0};
	
	static class Room {
		int x;
		int y;
		int z;
		
		Room(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

	}
	
	static boolean check(int x, int y, int z) {
		return 0 <= x && x < L && 0 <= y && y < R && 0 <= z && z < C;
	}
	
	private static void bfs(Room start, Room end) {
		int startX = start.x;
		int startY = start.y;
		int startZ = start.z;
		building[startX][startY][startZ] = 1;
		Queue<Room> que = new LinkedList<>();
		que.offer(start);
		while (!que.isEmpty()) {
			Room current = que.poll();
			if (current.equals(end)) {
				return;
			}
			for (int i = 0; i < 6; i++) {
				int nextX = current.x + dx[i];
				int nextY = current.y + dy[i];
				int nextZ = current.z + dz[i];
				if (check(nextX, nextY, nextZ) && building[nextX][nextY][nextZ] == 0) {
					building[nextX][nextY][nextZ] = building[current.x][current.y][current.z] + 1; 
					que.offer(new Room(nextX, nextY, nextZ));
				}
			}
		}
	}
	
	static void printAnswer(Room end) throws IOException {
		int num = building[end.x][end.y][end.z];
		if (num == 0) {
			bw.write("Trapped!\n");
		} else {
			bw.write("Escaped in " + (num - 1) + " minute(s).\n");
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		do {
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if (L == 0 && R == 0 && C == 0) {
				break;
			}
			building = new int[L][R][C];
			Room start = null;
			Room end = null;
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					String col = br.readLine();
					for (int k = 0; k < C; k++) {
						char ch = col.charAt(k);
						if (ch == '#') {
							building[i][j][k] = -1;
						} else if (ch == 'S') {
							start = new Room(i, j, k);
						} else if (ch == 'E') {
							end = new Room(i, j, k);
						} 
					}
				}
				br.readLine();
			}
			bfs(start, end);
			printAnswer(end);
		} while (!(L == 0 && R == 0 && C == 0));
		
		bw.flush();
		br.close();
		bw.close();
	}
}