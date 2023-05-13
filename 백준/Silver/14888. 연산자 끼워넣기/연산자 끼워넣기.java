import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] nums = new int[11];
    static int[] operators = new int[4];    // +,-,*,/
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        func(nums[0], 1, operators[0], operators[1], operators[2], operators[3]);
        System.out.println(max);
        System.out.println(min);
    }

    private static void func(int res, int idx, int plus, int minus, int multi, int division) {
        if (idx == N) {
            min = Math.min(min, res);
            max = Math.max(max, res);
            return;
        }
        if (plus > 0) {
            func(res + nums[idx], idx + 1, plus - 1, minus, multi, division);
        }
        if (minus > 0) {
            func(res - nums[idx], idx + 1, plus, minus - 1, multi, division);
        }
        if (multi > 0) {
            func(res * nums[idx], idx + 1, plus, minus, multi - 1, division);
        }
        if (division > 0) {
            func(res / nums[idx], idx + 1, plus, minus, multi, division - 1);
        }
    }
}