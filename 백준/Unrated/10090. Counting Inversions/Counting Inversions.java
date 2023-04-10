import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr = new int[1000001];
    static int[] tmp = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(countInversions(1, N));
    }

    private static long countInversions(int start, int end) {
        if (start == end) return 0;
        int mid = (start + end) / 2;
        return countInversions(start, mid) + countInversions(mid + 1, end) + merge(start, mid, end);
    }

    private static long merge(int start, int mid, int end) {
        int l = start;
        int r = mid + 1;
        int idx = l;
        long inversionCnt = 0;
        while (l <= mid && r <= end) {
            if (arr[l] < arr[r]) {
                tmp[idx++] = arr[l++];
            } else {
                tmp[idx++] = arr[r++];
                inversionCnt += mid - l + 1;
            }
        }
        while (l <= mid) tmp[idx++] = arr[l++];
        while (r <= end) tmp[idx++] = arr[r++];
        for (int i = start; i <= end; i++) {
            arr[i] = tmp[i];
        }
        return inversionCnt;
    }
}