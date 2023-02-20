import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int gcd = scanner.nextInt();    // A,B 의 최대공약수
        int lcm = scanner.nextInt();    // A,B 의 최소공배수
        int ab = lcm / gcd;         // A = gcd * a, B = gcd * b, AB = lcm * gcd
        int[] result = new int[3];  // {합, a, b}
        result[0] = Integer.MAX_VALUE;
        for (int a = 1; a * a <= ab; a++) {
            if (ab % a != 0) continue;
            int b = ab / a;
            if (!isCoPrime(a, b)) continue;  // a, b 는 서로소여야만 함.
            int A = gcd * a;
            int B = gcd * b;
            int sum = A + B;
            if (result[0] > sum) {
                result[0] = sum;
                result[1] = A;
                result[2] = B;
            }
        }
        System.out.println(result[1] + " " + result[2]);
    }

    private static boolean isCoPrime(int a, int b) {
        for (int i = 2; i * i <= a; i++) {
            if (a % i == 0 && (b % i == 0 || b % (a / i) == 0)) return false;
        }
        return true;
    }
}