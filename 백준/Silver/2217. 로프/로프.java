import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ropes = new int[N];
        for (int i = 0; i < N; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(ropes);

        int result = 0;
        // 로프를 i개 사용할 때의 들어올릴 수 있는 물체의 최대 중량
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, ropes[N - i] * i);
        }
        System.out.println(result);
    }
}