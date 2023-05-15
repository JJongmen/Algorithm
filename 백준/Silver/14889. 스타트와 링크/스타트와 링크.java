import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] board = new int[20][20];
    static int[] teamA = new int[10];
    static int[] teamB = new int[10];
    static boolean[] hasTeam = new boolean[20];
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        func(0, -1);
        System.out.println(result);
    }

    private static void func(int cur, int last) {
        if (cur == N / 2) {
            int idx = 0;
            for (int i = 0; i < N; i++) {
                if (hasTeam[i]) continue;
                teamB[idx++] = i;
            }
            int sumA = 0;
            int sumB = 0;
            for (int i = 0; i < N / 2 - 1; i++) {
                for (int j = i + 1; j < N / 2; j++) {
                    sumA += board[teamA[i]][teamA[j]] + board[teamA[j]][teamA[i]];
                    sumB += board[teamB[i]][teamB[j]] + board[teamB[j]][teamB[i]];

                }
            }
            result = Math.min(result, Math.abs(sumA - sumB));
            return;
        }
        for (int i = last + 1; i < N; i++) {
            teamA[cur] = i;
            hasTeam[i] = true;
            func(cur + 1, i);
            hasTeam[i] = false;
        }
    }
}