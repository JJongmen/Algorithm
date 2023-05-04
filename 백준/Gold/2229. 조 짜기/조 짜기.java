import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] scores = new int[1001];
    static int[] memo = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            int max = 0;
            int min = 10000;
            for (int j = i; j >= 1; j--) {
                max = Math.max(max, scores[j]);
                min = Math.min(min, scores[j]);
                memo[i] = Math.max(memo[i], max - min + memo[j - 1]);
            }
        }
        System.out.println(memo[N]);
    }
}