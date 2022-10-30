import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] nums = new int[2000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums, 0, N);

        int result = 0;
        for (int i = 0; i < N; i++) {
            result += isGoodNumber(i);
        }
        System.out.println(result);
    }

    private static int isGoodNumber(int i) {
        int l = 0;
        int r = N - 1;
        if (l == i) l++;
        if (r == i) r--;
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum > nums[i]) r--;
            else if (sum < nums[i]) l++;
            else return 1;
            if (l == i) l++;
            if (r == i) r--;
        }
        return 0;
    }
}