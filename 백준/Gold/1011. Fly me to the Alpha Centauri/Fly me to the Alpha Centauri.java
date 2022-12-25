import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T-- > 0) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            System.out.println(getMinJump(x, y));
        }
    }

    private static int getMinJump(int x, int y) {
        int dist = y - x;
        double sqrt = Math.sqrt(dist);
        int max = (int) sqrt;
        if (sqrt % 1 == 0) {
            return max * 2 - 1;
        }
        if (dist <= max * max + max) {
            return max * 2;
        }
        return max * 2 + 1;
    }
}