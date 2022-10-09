import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] scores = new int[105];
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            scores[i] = Integer.parseInt(br.readLine());
        }

        int result = 0;
        for (int i = N - 2; i >= 0; i--) {
            if (scores[i] < scores[i + 1]) continue;
            result += scores[i] - scores[i + 1] + 1;
            scores[i] = scores[i + 1] - 1;
        }
        System.out.println(result);
    }
}