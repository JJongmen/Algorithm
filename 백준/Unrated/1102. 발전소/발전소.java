import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 10000;
    static int N, P;
    static int[][] restoreCost = new int[16][16];
    static int[][] memo = new int[17][1 << 16 + 1];

    static class PowerStatus {
        int status, cnt, cost;

        public PowerStatus(int status, int cnt, int cost) {
            this.status = status;
            this.cnt = cnt;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                restoreCost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        String powerStr = br.readLine();
        int status = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (powerStr.charAt(i) == 'Y') {
                status |= (1 << i);
                cnt++;
            }
        }
        P = Integer.parseInt(br.readLine());

        Queue<PowerStatus> que = new LinkedList<>();
        que.offer(new PowerStatus(status, cnt, 0));
        for (int i = 0; i <= N; i++) {
            Arrays.fill(memo[i], INF);
        }
        memo[cnt][status] = 0;
        while (!que.isEmpty()) {
            PowerStatus cur = que.poll();
            if (cur.cnt >= P) break;
            if (memo[cur.cnt][cur.status] < cur.cost) continue;
            // 켜져 있는 발전소를 찾는다.
            for (int i = 0; i < N; i++) {
                if ((cur.status & (1 << i)) == 0) continue;
                // 다른 꺼져 있는 발전소를 찾는다.
                for (int j = 0; j < N; j++) {
                    if ((cur.status & (1 << j)) > 0 || i == j) continue;
                    int nxtStatus = cur.status | (1 << j);
                    int nxtCost = cur.cost + restoreCost[i][j];
                    int nxtCnt = cur.cnt + 1;
                    if (memo[nxtCnt][nxtStatus] <= nxtCost) continue;
                    memo[nxtCnt][nxtStatus] = nxtCost;
                    que.offer(new PowerStatus(nxtStatus, nxtCnt, nxtCost));
                }
            }
        }
        int min = INF;
        for (int i = P; i <= N; i++) {
            for (int j = 0; j < 1 << N; j++) {
                min = Math.min(min, memo[i][j]);
            }
        }
        System.out.println(min == INF ? -1 : min);
    }
}