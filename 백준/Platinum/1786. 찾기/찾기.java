import java.util.Scanner;

public class Main {

    static int[] failure(String s) {
        int[] f = new int[s.length()];
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) j = f[j - 1];
            if (s.charAt(i) == s.charAt(j)) f[i] = ++j;
        }
        return f;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String T = scan.nextLine();
        String P = scan.nextLine();

        int[] f = failure(P);
        int cnt = 0;
        StringBuilder result = new StringBuilder();
        int j = 0;
        for (int i = 0; i < T.length(); i++) {
            while (j > 0 && T.charAt(i) != P.charAt(j)) j = f[j - 1];
            if (T.charAt(i) == P.charAt(j)) j++;
            if (j == P.length()) {
                cnt++;
                result.append(i - j + 2).append(" ");
                j = f[j - 1];
            }
        }
        System.out.println(cnt);
        System.out.println(result);
    }
}