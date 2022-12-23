import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int A = scan.nextInt();
        int Pa = scan.nextInt();
        int B = scan.nextInt();
        int Pb = scan.nextInt();
        int aMax = N / Pa;
        long result = 0;
        long resultA = 0;
        long resultB = 0;
        for (long aCnt = 0; aCnt <= aMax; aCnt++) {
            long bCnt = (N - Pa * aCnt) / Pb;
            long sum = A * aCnt + B * bCnt;
            if (result < sum) {
                result = sum;
                resultA = aCnt;
                resultB = bCnt;
            }
        }
        System.out.println(resultA + " " + resultB);
    }
}