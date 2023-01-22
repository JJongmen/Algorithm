import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int K = scan.nextInt();
        char[] num = scan.next().toCharArray();

        StringBuilder sb = new StringBuilder();
        for (char c : num) {
            if (K == 0 || sb.length() == 0) {
                sb.append(c);
            } else {
                while (K > 0 && sb.length() > 0 && sb.charAt(sb.length() - 1) < c) {
                    sb.deleteCharAt(sb.length() - 1);
                    K--;
                }
                sb.append(c);
            }
        }
        System.out.println(sb.substring(0, sb.length() - K));
    }
}