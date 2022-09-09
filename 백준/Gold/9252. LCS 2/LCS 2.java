import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str1 = scan.nextLine();
        String str2 = scan.nextLine();
        int[][] memo = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                } else {
                    memo[i][j] = Math.max(memo[i][j - 1], memo[i - 1][j]);
                }
            }
        }

        int temp = memo[str1.length()][str2.length()];
        System.out.println(temp);
        if (temp == 0) {
            return;
        }

        int maxI = str1.length();
        int maxJ = str2.length();
        StringBuilder sb = new StringBuilder();
        for (int i = maxI; i >= 1; i--) {
            for (int j = maxJ; j >= 1; j--) {
                if (memo[i][j] < temp) {
                    break;
                }
                if (memo[i - 1][j] == temp - 1 && memo[i][j - 1] == temp - 1) {
                    sb.append(str1.charAt(i - 1));
                    maxI = i - 1;
                    maxJ = j - 1;
                    temp--;
                    break;
                }
            }
        }
        System.out.println(sb.reverse());
    }
}
