import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] nums = new int[1_000_005];
    static int[] lis = new int[1_000_005];
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        lis[size++] = nums[0];
        for (int i = 1; i < N; i++) {
            if (lis[size - 1] < nums[i]) {
                lis[size++] = nums[i];
            } else {
                int replaceIdx = lowerBound(lis, 0, size, nums[i]);
                lis[replaceIdx] = nums[i];
            }
        }
        System.out.println(size);
    }
    
    private static int lowerBound(int[] arr, int st, int en, int target) {
        while (st < en) {
            int mid = (st + en) / 2;
            if (target <= arr[mid]) en = mid;
            else st = mid + 1;
        }
        return st;
    }
}