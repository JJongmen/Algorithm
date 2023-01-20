import java.util.Scanner;

public class Main {
	static boolean[][][] visit;
	static int A, B, C;
	static boolean[] possible;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		A = scan.nextInt();
		B = scan.nextInt();
		C = scan.nextInt();

		visit = new boolean[A + 1][B + 1][C + 1];
		possible = new boolean[C + 1];
		dfs(0, 0, C);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= C; i++) {
			if (!possible[i]) continue;
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}

	private static void dfs(int x, int y, int z) {
		if (visit[x][y][z]) return;
		if (x == 0) {
			possible[z] = true;
		}
		visit[x][y][z] = true;
		int give = Math.min(x, B - y);
		dfs(x - give, y + give, z);
		give = Math.min(x, C - z);
		dfs(x - give, y, z + give);
		give = Math.min(y, A - x);
		dfs(x + give, y - give, z);
		give = Math.min(y, C - z);
		dfs(x, y - give, z + give);
		give = Math.min(z, A - x);
		dfs(x + give, y, z - give);
		give = Math.min(z, B - y);
		dfs(x, y + give, z - give);
	}
}