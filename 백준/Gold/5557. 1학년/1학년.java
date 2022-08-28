import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static long solve(int N, int[] nums) {
        long[][] memo = new long[N - 1][21];
        memo[0][nums[0]] = 1;
        for (int i = 1; i < N - 1; i++) {
            FillMemoUntilI(nums, memo, i);
        }
        return memo[N - 2][nums[N - 1]];
    }

    private static void FillMemoUntilI(int[] nums, long[][] memo, int i) {
        for (int j = 0; j <= 20; j++) {
            if (memo[i - 1][j] != 0) {
                if (0 <= j + nums[i] && j + nums[i] <= 20) {
                    memo[i][j + nums[i]] += memo[i - 1][j];
                }
                if (0 <= j - nums[i] && j - nums[i] <= 20) {
                    memo[i][j - nums[i]] += memo[i - 1][j];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(solve(N, nums));
    }
}