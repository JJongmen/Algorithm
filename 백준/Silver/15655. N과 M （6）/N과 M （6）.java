import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] nums = new int[10];
    private static int[] arr = new int[10];
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static int M;

    private static void bt(int cur, int lastUsedIdx) throws IOException {
        if (cur == M) {
            for (int i = 0; i < M; i++) {
                bw.write(arr[i] + " ");
            }
            bw.write('\n');
            return;
        }
        for (int i = lastUsedIdx + 1; i < N; i++) {
            arr[cur] = nums[i];
            bt(cur + 1, i);
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
        Arrays.sort(nums,0, N);
        bt(0, -1);
        bw.flush();
        bw.close();
        br.close();
    }
}