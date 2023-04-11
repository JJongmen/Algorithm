import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr = new int[500000];
    static int[] tmp = new int[500000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(merge_sort(0, N - 1));
    }

    private static long merge_sort(int start, int end) {
        if (start == end) return 0;
        int mid = (start + end) / 2;
        return merge_sort(start, mid) + merge_sort(mid + 1, end) + merge(start, end);
    }

    private static long merge(int start, int end) {
        int mid = (start + end) / 2;
        int l = start;
        int r = mid + 1;
        int idx = start;
        long cnt = 0;
        while (l <= mid && r <= end) {
            if (arr[l] <= arr[r]) {
                tmp[idx++] = arr[l++];
            } else {
                tmp[idx++] = arr[r++];
                cnt += mid - l + 1;
            }
        }
        while (l <= mid) tmp[idx++] = arr[l++];
        while (r <= end) tmp[idx++] = arr[r++];
        for (int i = start; i <= end; i++) {
            arr[i] = tmp[i];
        }
        return cnt;
    }
}