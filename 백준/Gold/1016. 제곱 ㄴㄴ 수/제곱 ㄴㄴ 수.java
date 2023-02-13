import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long min = scanner.nextLong();
        long max = scanner.nextLong();

        int max_prime = (int) Math.sqrt(max);
        boolean[] isPrime = new boolean[max_prime + 1];
        Arrays.fill(isPrime, 2, max_prime + 1, true);

        int range = (int) (max - min) + 1;
        boolean[] isSquareMultiple = new boolean[range];
        int cnt = range;

        for (int i = 2; i <= max_prime; i++) {
            if (!isPrime[i]) continue;
            long sqrNum = (long) i * i;
            for (long j = min % sqrNum == 0 ? min : sqrNum * (min / sqrNum + 1); j <= max; j += sqrNum) {
                if (isSquareMultiple[(int) (j - min)]) continue;
                isSquareMultiple[(int) (j - min)] = true;
                cnt--;
            }
            for (long j = sqrNum; j <= max_prime; j += i) {
                isPrime[(int) j] = false;
            }
        }
        System.out.println(cnt);
    }
}