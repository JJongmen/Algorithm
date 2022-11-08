import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N, M, K;
    static char[][] board = new char[13][13];
    static Map<String, Integer> messageCnts = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                board[i][j] = line.charAt(j - 1);
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dfs(i, j, String.valueOf(board[i][j]), 1);
            }
        }

        while (K-- > 0) {
            bw.write(messageCnts.getOrDefault(br.readLine(), 0) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y, String tmp, int cnt) {
        messageCnts.put(tmp, messageCnts.getOrDefault(tmp, 0) + 1);
        if (cnt == 5) {
            return;
        }
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx == 0) nx = N;
            else if (nx == N + 1) nx = 1;
            if (ny == 0) ny = M;
            else if (ny == M + 1) ny = 1;
            dfs(nx, ny, tmp + board[nx][ny], cnt + 1);
        }
    }
}