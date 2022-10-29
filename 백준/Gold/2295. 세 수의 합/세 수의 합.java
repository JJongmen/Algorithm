import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] sum = new int[501000];
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(array);

        for (int x = 0; x < N; x++) {
            for (int y = x; y < N; y++) {
                sum[size++] = array[x] + array[y];
            }
        }
        Arrays.sort(sum, 0, size);

        for (int k = N - 1; k > 0; k--) {
            if (isMaxK(array, k)) {
                System.out.println(array[k]);
                return;
            }
        }
    }

    private static boolean isMaxK(int[] array, int k) {
        for (int z = k - 1; z >= 0; z--) {
            if (binarySearch(array[k] - array[z])) {
                return true;
            }
        }
        return false;
    }

    static boolean binarySearch(int target) {
        int st = 0;
        int en = size - 1;
        while (st <= en) {
            int mid = (st + en) / 2;
            if (sum[mid] > target) en = mid - 1;
            else if (sum[mid] < target) st = mid + 1;
            else return true;
        }
        return false;
    }
}