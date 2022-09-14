import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        int[][] memo = new int[n][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = memo[0][0] = nums[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            memo[i][0] = Math.max(nums[i], memo[i - 1][0] + nums[i]);
            memo[i][1] = Math.max(memo[i - 1][0], memo[i - 1][1] + nums[i]);
            max = Math.max(max, Math.max(memo[i][0], memo[i][1]));
        }

        System.out.println(max);
    }
}