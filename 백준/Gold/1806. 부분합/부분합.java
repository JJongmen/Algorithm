import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] seq = new int[100000];
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int r = 0;
        long sum = 0;
        for (int l = 0; l < N; l++) {
            while (r < N && sum < S) {
                sum += seq[r++];
            }
            if (sum >= S) {
                result = Math.min(result, r - l);
            }
            sum -= seq[l];
        }
        System.out.println(result == Integer.MAX_VALUE ? 0 : result);
    }
}