import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int K = scan.nextInt();

        // left <= x <= right
        int left = 1;
        int right = K;

        // lower bound
        while (left < right) {
            int mid = (left + right) / 2;

            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                cnt += Math.min(N, mid / i);
            }

            if (cnt < K) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(left);
    }

}