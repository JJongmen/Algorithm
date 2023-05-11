import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_LENGTH = 1000;
    static int N;
    static int[] arr = new int[MAX_LENGTH];
    static int[] memo = new int[MAX_LENGTH];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            memo[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] >= arr[i]) continue;
                memo[i] = Math.max(memo[i], memo[j] + arr[i]);
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, memo[i]);
        }
        System.out.println(result);
    }
}