import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        long ans = 1;
        for (int i = 2; i <= N; i++) {
            ans *= i;
        }
        System.out.println(ans);
    }
}
