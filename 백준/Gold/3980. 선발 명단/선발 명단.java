import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] stats;
    static boolean[] visit;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            stats = new int[11][11];
            visit = new boolean[11];
            max = 0;
            for (int i = 0; i < 11; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    stats[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            bt(0, 0);
            bw.write(max + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bt(int cur, int sum) {
        if (cur == 11) {
            max = Math.max(max, sum);
            return;
        }
        for (int i = 0; i < 11; i++) {
            if (visit[i] || stats[cur][i] == 0) continue;
            visit[i] = true;
            bt(cur + 1, sum + stats[cur][i]);
            visit[i] = false;
        }
    }
}
