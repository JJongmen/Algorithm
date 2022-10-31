import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = Integer.MAX_VALUE;
    static int N;
    static int[] values = new int[100000];
    static int result = MAX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = N - 1;
        while (l < r) {
            int sum = values[l] + values[r];
            if (Math.abs(sum) < Math.abs(result)) {
                result = sum;
            }
            if (sum < 0) l++;
            else if (sum > 0) r--;
            else break;
        }
        System.out.println(result);
    }
}