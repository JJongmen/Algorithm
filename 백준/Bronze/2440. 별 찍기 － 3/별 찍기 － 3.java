import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        StringBuilder sb = new StringBuilder();
        while (N > 0) {
            for (int i = 0; i < N; i++) {
                sb.append('*');
            }
            sb.append('\n');
            N--;
        }
        System.out.println(sb);
    }
}