import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(nums, 0, N);

        int result = Integer.MAX_VALUE;
        int l = 0;
        int r = 0;
        while (l < N && r < N) {
            int diff = nums[r] - nums[l];
            if (diff >= M) {
                result = Math.min(result, diff);
                l++;
            } else {
                r++;
            }
        }

        System.out.println(result);
    }
}