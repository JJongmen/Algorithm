import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int T, n, m;
    private static long result;
    private static long[] arr = new long[1005];
    private static long[] brr = new long[1005];
    private static long[] subSumA = new long[501005];
    private static long[] subSumB = new long[501005];
    private static int sizeA, sizeB;

    private static int upperBound(long target) {
        int l = 0;
        int r = sizeB;
        while (l < r) {
            int mid = (l + r) / 2;
            if (subSumB[mid] <= target) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    private static int lowerBound(long target) {
        int l = 0;
        int r = sizeB;
        while (l < r) {
            int mid = (l + r) / 2;
            if (subSumB[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            brr[i] = Long.parseLong(st.nextToken());
        }

        prefixSum();
        subSum();
        Arrays.sort(subSumB, 0, sizeB);
        for (int i = 0; i < sizeA; i++) {
            long target = T - subSumA[i];
            result += upperBound(target) - lowerBound(target);
        }
        System.out.println(result);
    }

    private static void subSum() {
        for (int i = 0; i <= n - 1; i++) {
            for (int j = i + 1; j <= n; j++) {
                subSumA[sizeA++] = arr[j] - arr[i];
            }
        }
        for (int i = 0; i <= m - 1; i++) {
            for (int j = i + 1; j <= m; j++) {
                subSumB[sizeB++] = brr[j] - brr[i];
            }
        }
    }

    private static void prefixSum() {
        for (int i = 1; i <= n; i++) {
            arr[i] += arr[i - 1];
        }
        for (int i = 1; i <= m; i++) {
            brr[i] += brr[i - 1];
        }
    }
}