import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] diameters = new int[605];
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            diameters[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(diameters, 0, N);

        for (int i = 0; i < N - 3; i++) {
            for (int j = i + 1; j < N; j++) {
                int target = diameters[i] + diameters[j];
                int l = i + 1;
                int r = N - 1;
                if (l == j) l++;
                if (r == j) r--;
                while (l < r) {
                    int sum = diameters[l] + diameters[r];
                    result = Math.min(result, Math.abs(sum - target));
                    if (sum < target) l++;
                    else if (sum > target) r--;
                    else {
                        System.out.println(0);
                        return;
                    }
                    if (l == j) l++;
                    if (r == j) r--;
                }
            }
        }
        System.out.println(result);
    }
}