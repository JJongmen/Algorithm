import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[] values = new long[5000];
    static long[] result = {Long.MAX_VALUE, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            values[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(values, 0, N);

        for (int i = 0; i < N - 2; i++) {
            int l = i + 1;
            int r = N - 1;
            while (l < r) {
                long sum = values[i] + values[l] + values[r];
                if (Math.abs(sum) < result[0]) {
                    result[0] = Math.abs(sum);
                    result[1] = values[i];
                    result[2] = values[l];
                    result[3] = values[r];
                }
                if (sum < 0) l++;
                else if (sum > 0) r--;
                else break;
            }
        }
        System.out.println(result[1] + " " + result[2] + " " + result[3]);
    }
}