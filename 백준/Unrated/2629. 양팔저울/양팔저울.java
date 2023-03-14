import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static final int ZERO_IDX = 15000;
    static int N;
    static int[] weight = new int[31];
    static boolean[][] memo = new boolean[31][30001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        memo[0][ZERO_IDX] = true;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= ZERO_IDX * 2; j++) {
                if (!memo[i - 1][j]) continue;
                memo[i][j + weight[i]] = true;
                memo[i][j] = true;
                memo[i][j - weight[i]] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (M-- > 0) {
            int checkWeight = Integer.parseInt(st.nextToken());
            if (checkWeight > 15000 || !memo[N][checkWeight + ZERO_IDX]) {
                sb.append("N ");
            } else {
                sb.append("Y ");
            }
        }
        System.out.println(sb);
    }
}