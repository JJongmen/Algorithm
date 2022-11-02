import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] nums = new int[10000];
    static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums, 0, N);
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                int target = -(nums[i] + nums[j]);
                result += upperBound(target, j + 1, N) - lowerBound(target, j + 1, N);
            }
        }
        System.out.println(result);
    }

    private static int upperBound(int target, int minIdx, int maxIdx) {
        int st = minIdx;
        int en = maxIdx;
        while (st < en) {
            int mid = (st + en) / 2;
            if (nums[mid] > target) en = mid;
            else st = mid + 1;
        }
        return st;
    }

    private static int lowerBound(int target, int minIdx, int maxIdx) {
        int st = minIdx;
        int en = maxIdx;
        while (st < en) {
            int mid = (st + en) / 2;
            if (nums[mid] >= target) en = mid;
            else st = mid + 1;
        }
        return st;
    }
}