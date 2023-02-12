import java.util.Scanner;

public class Main {
    static int[] memo = new int[50];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        System.out.println(fibo(N));
    }

    private static int fibo(int n) {
        if (n <= 1) {
            return n;
        }

        if (memo[n] != 0) {
            return memo[n];
        }

        return memo[n] = fibo(n - 1) + fibo(n - 2);
    }
}