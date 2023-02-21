import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] diff = new int[N - 1];
        st = new StringTokenizer(br.readLine());
        int prev = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N - 1; i++) {
            int cur = Integer.parseInt(st.nextToken());
            diff[i] = cur - prev;
            prev = cur;
        }

        Arrays.sort(diff);
        int cost = 0;
        for (int i = 0; i < N - K; i++) {
            cost += diff[i];
        }
        System.out.println(cost);
    }
}