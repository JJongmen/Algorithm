import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private final static int INF = 100000001;
    private static int[][] nexts;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] costs = new int[n + 1][n + 1];
        nexts = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                costs[i][j] = INF;
            }
            costs[i][i] = 0;
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (costs[a][b] > c) {
                costs[a][b] = c;
                nexts[a][b] = b;
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (costs[i][j] > costs[i][k] + costs[k][j]) {
                        costs[i][j] = costs[i][k] + costs[k][j];
                        nexts[i][j] = nexts[i][k];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (costs[i][j] == INF) {
                    bw.write("0 ");
                    continue;
                }
                bw.write(costs[i][j] + " ");
            }
            bw.write("\n");
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (nexts[i][j] == 0) {
                    bw.write("0\n");
                    continue;
                }
                int next = i;
                List<Integer> route = new ArrayList<>();
                while (next != j) {
                    route.add(next);
                    next = nexts[next][j];
                }
                route.add(next);
                bw.write(route.size() + " ");
                for (int nextCity : route) {
                    bw.write(nextCity + " ");
                }
                bw.write("\n");
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }
}