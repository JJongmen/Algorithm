import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] nums = new int[10];
    private static int[] arr = new int[10];
    private static int N;
    private static int M;
    private static StringBuilder ans = new StringBuilder();

    private static void func(int cur) throws IOException {
        if (cur == M) {
            for (int i = 0; i < M; i++) {
                ans.append(arr[i]).append(" ");
            }
            ans.append("\n");
            return;
        }
        for (int i = 0; i < N; i++) {
            arr[cur] = nums[i];
            func(cur + 1);
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
        func(0);

        System.out.println(ans);
    }
}