import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int longCnt = N / 4;
        StringBuilder sb = new StringBuilder();
        while (longCnt-- > 0) {
            sb.append("long ");
        }
        sb.append("int");
        System.out.println(sb);
    }
}