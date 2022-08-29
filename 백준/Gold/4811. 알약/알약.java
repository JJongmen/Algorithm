import java.io.*;

public class Main {

    static long[][] memo = new long[31][31];
    static {
        for (int i = 0; i <= 30; i++) {
            memo[0][i] = 1;
        }
    }

    static long recur(int x, int y) {
        if (y < 0) {
            return 0;
        }
        if (memo[x][y] != 0) {
            return memo[x][y];
        }
        if (x == 0) {
            memo[0][y] = 1;
            return 1;
        }
        return memo[x][y] = recur(x - 1, y + 1) + recur(x, y - 1);
    }

    public static long solve(int N) {
        return recur(N, 0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = 0;
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            bw.write(solve(N) + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}