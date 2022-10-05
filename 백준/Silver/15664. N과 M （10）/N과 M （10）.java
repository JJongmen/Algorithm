import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;
    private static int[] nums = new int[10];
    private static int[] arr = new int[10];
    private static StringBuilder result = new StringBuilder();

    private static void sequence(int cur, int idx) {
        if (cur == M) {
            for (int i = 0; i < M; i++) {
                result.append(arr[i]).append(" ");
            }
            result.append("\n");
            return;
        }
        int tmp = 0;
        for (int i = idx; i < N; i++) {
            if (tmp != nums[i]) {
                arr[cur] = nums[i];
                tmp = arr[cur];
                sequence(cur + 1, i + 1);
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums, 0, N);
        sequence(0, 0);
        System.out.println(result);
    }
}