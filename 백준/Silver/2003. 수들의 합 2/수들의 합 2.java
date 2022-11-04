import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] seq = new int[10000];
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int r = 0;
        long sum = seq[0];
        for (int l = 0; l < N; l++) {
            while (r < N && sum < M) {
                r++;
                if (r != N) sum += seq[r];
            }
            if (r == N) break;
            if (sum == M) result++;
            sum -= seq[l];
        }

        System.out.println(result);
    }
}