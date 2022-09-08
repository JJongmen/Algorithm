import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N + 1];
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());

        int[][] memo = new int[N + 1][N + 1];
        for (int i = N; i >= 1; i--) {
            for (int j = i; j <= N; j++) {
                if (i == j) {
                    memo[i][j] = 1;
                } else if (i + 1 == j) {
                    memo[i][j] = nums[i] == nums[j] ? 1 : 0;
                } else {
                    memo[i][j] = memo[i + 1][j - 1] == 1 && nums[i] == nums[j]
                            ? 1 : 0;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            bw.write(memo[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}