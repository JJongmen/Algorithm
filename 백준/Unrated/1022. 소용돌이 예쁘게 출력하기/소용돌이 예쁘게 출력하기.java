import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int r1 = scanner.nextInt();
        int c1 = scanner.nextInt();
        int r2 = scanner.nextInt();
        int c2 = scanner.nextInt();

        int[][] tornado = new int[r2 - r1 + 1][c2 - c1 + 1];
        int max = 0;
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                tornado[i - r1][j - c1] = getNum(i, j);
                max = Math.max(max, tornado[i - r1][j - c1]);
            }
        }

        int maxLen = getLength(max);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= r2 - r1; i++) {
            for (int j = 0; j <= c2 - c1; j++) {
                int preBlank = maxLen - getLength(tornado[i][j]);
                while (preBlank-- > 0) {
                    sb.append(' ');
                }
                sb.append(tornado[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static int getLength(int num) {
        int len = 0;
        while (num > 0) {
            num /= 10;
            len++;
        }
        return len;
    }

    private static int getNum(int x, int y) {
        if (Math.abs(x) >= Math.abs(y)) {
            if (x >= 0) {
                return 4 * x * x + 3 * x + y + 1;
            }
            return 4 * x * x + x - y + 1;
        }
        if (y > 0) {
            return 4 * y * y - x - 3 * y + 1;
        }
        return 4 * y * y + x - y + 1;
    }
}