import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static long[] arr = new long[1000005];
    static int maxLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            arr[l + 1]++;
            arr[r + 1]--;
            maxLength = Math.max(maxLength, r);
        }

        for (int i = 1; i <= maxLength; i++) {
            arr[i] += arr[i - 1];
        }

        for (int i = 1; i <= maxLength; i++) {
            arr[i] += arr[i - 1];
        }

        for (int l = 0; l < maxLength; l++) {
            long target = K + arr[l];
            if (target <= arr[l]) {
                break;
            }
            int r = Arrays.binarySearch(arr, l, maxLength + 1, target);
            if (r > l) {
                System.out.printf("%d %d", l, r);
                return;
            }
        }
        System.out.println("0 0");
    }
}