import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String S = scan.next();
        char last = S.charAt(0);
        int result = 1;
        for (int i = 1; i < S.length(); i++) {
            if (last == S.charAt(i)) continue;
            last = S.charAt(i);
            result++;
        }
        System.out.println(result / 2);
    }
}