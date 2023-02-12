import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    static BigInteger[] memo = new BigInteger[10005];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        memo[0] = BigInteger.ZERO;
        memo[1] = BigInteger.ONE;
        for (int i = 2; i <= 10000; i++) {
            memo[i] = memo[i - 1].add(memo[i - 2]);
        }
        System.out.println(memo[N]);
    }

}