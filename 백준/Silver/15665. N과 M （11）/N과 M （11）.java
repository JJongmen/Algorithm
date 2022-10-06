import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;
    private static int size = 0;
    private static int[] arr = new int[10];
    private static int[] nums = new int[10];
    private static boolean[] isExist = new boolean[10005];
    private static StringBuilder result = new StringBuilder();

    private static void func(int cur) {
        if (cur == M) {
            for (int i = 0; i < M; i++) {
                result.append(arr[i]).append(' ');
            }
            result.append('\n');
            return;
        }
        for (int i = 0; i < size; i++) {
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
            int num = Integer.parseInt(st.nextToken());
            if (isExist[num]) continue;
            nums[size++] = num;
            isExist[num] = true;
        }
        Arrays.sort(nums, 0, size);
        func(0);
        System.out.println(result);
    }
}