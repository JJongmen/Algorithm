import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();
        double c = scanner.nextDouble();

        double l = 0;
        double r = Math.min(x, y);
        while (r - l > 0.001) {
            double mid = (l + r) / 2;

            double h1 = Math.sqrt(x * x - mid * mid);
            double h2 = Math.sqrt(y * y - mid * mid);

            double res = h1 * h2 / (h1 + h2);
            if (res >= c) l = mid;
            else r = mid;
        }
        System.out.printf("%.3f", r);
    }
}