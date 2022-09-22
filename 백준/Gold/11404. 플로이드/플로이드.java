import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] minCosts = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                minCosts[i][j] = Integer.MAX_VALUE;
            }
            minCosts[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            minCosts[a][b] = Math.min(minCosts[a][b], c);
        }

        // 도시 k를 거쳐서 지나갈 때
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (minCosts[i][k] != Integer.MAX_VALUE && minCosts[k][j] != Integer.MAX_VALUE) {
                        minCosts[i][j] = Math.min(minCosts[i][j], minCosts[i][k] + minCosts[k][j]);
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (minCosts[i][j] == Integer.MAX_VALUE) {
                    bw.write("0 ");
                } else {
                    bw.write(minCosts[i][j] + " ");
                }
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}