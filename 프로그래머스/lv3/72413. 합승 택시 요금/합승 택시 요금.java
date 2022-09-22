class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int INF = 40000001;
        int[][] minCosts = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                minCosts[i][j] = INF;
            }
            minCosts[i][i] = 0;
        }

        for (int[] fare : fares) {
            int x = fare[0];
            int y = fare[1];
            int z = fare[2];
            minCosts[x][y] = z;
            minCosts[y][x] = z;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    minCosts[i][j] = Math.min(minCosts[i][j], minCosts[i][k] + minCosts[k][j]);
                }
            }
        }

        int minCost = INF;
        for (int k = 1; k <= n; k++) {
            minCost = Math.min(minCost, minCosts[s][k] + minCosts[k][a] + minCosts[k][b]);
        }

        return minCost;
    }
}