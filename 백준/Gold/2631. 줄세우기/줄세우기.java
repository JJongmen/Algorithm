import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] children = new int[N];
        for (int i = 0; i < N; i++) {
            children[i] = Integer.parseInt(br.readLine());
        }

        int[] memo = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            int temp = 0;
            for (int j = 0; j < i; j++) {
                if (children[j] < children[i]) {
                    temp = Math.max(temp, memo[j]);
                }
            }
            memo[i] = temp + 1;
            max = Math.max(max, memo[i]);
        }

        System.out.println(N - max);
    }
}