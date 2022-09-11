import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String code = scan.nextLine();
        int len = code.length();

        // 첫번째 수가 0인 경우 암호해석이 불가능하다
        if (code.charAt(0) == '0') {
            System.out.println(0);
            return;
        }
        int[] memo = new int[code.length() + 1];
        memo[0] = memo[1] = 1;
        for (int i = 2; i <= code.length(); i++) {
            if (code.charAt(i - 1) != '0') {
                memo[i] = memo[i - 1];
            }
            int temp = Integer.parseInt(code.substring(i - 2, i));
            if (10 <= temp && temp <= 26) {
                memo[i] += memo[i - 2];
            }
            // i번째 수가 0이고 i-1번째 수가 1 또는 2가 아닌경우 해석이 불가능하다
            if (memo[i] == 0) {
                System.out.println(0);
                return;
            }
            memo[i] %= 1000000;
        }

        System.out.println(memo[len]);
    }
}