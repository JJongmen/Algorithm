import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = Integer.MAX_VALUE;
    static int N;
    static int[] values = new int[100000];
    static int[] result = {MAX, 0, 0};   // (특성값, 용액1, 용액2)

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
            if (Math.abs(sum) < result[0]) {
                result[0] = Math.abs(sum);
                result[1] = values[l];
                result[2] = values[r];
            }
            if (sum < 0) l++;
            else if (sum > 0) r--;
            else break;
        }
        System.out.println(result[1] + " " + result[2]);
    }
}