import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[] ropes = new int[100005];
        for (int i = 0; i < N; i++) {
            ropes[i] = scan.nextInt();
        }
        Arrays.sort(ropes, 0, N);

        int result = 0;
        // 로프를 i개 사용할 때의 들어올릴 수 있는 물체의 최대 중량
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, ropes[N - i] * i);
        }
        System.out.println(result);
    }
}