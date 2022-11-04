import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;
    static int size;
    static int[] primes = new int[300000];
    static boolean[] isPrime = new boolean[4_000_005];
    static int result;

    static {
        Arrays.fill(isPrime, true);
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        if (N == 1) {
            System.out.println(0);
            return;
        }

        for (int i = 2; i * i <= N; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j <= N; j += i) {
                isPrime[j] = false;
            }
        }

        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primes[size++] = i;
            }
        }

        int en = 0;
        int sum = 2;
        for (int st = 0; st < size; st++) {
            while (en < size && sum < N) {
                en++;
                if (en != size) sum += primes[en];
            }
            if (en == size) break;
            if (sum == N) result++;
            sum -= primes[st];
        }

        System.out.println(result);
    }
}