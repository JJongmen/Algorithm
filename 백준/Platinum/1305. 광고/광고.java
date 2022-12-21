import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int L = scan.nextInt();
        String s = scan.next();
        System.out.println(L - failure(s)[L - 1]);
    }

    private static int[] failure(String s) {
        int[] f = new int[s.length()];
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) j = f[j - 1];
            if (s.charAt(i) == s.charAt(j)) f[i] = ++j;
        }
        return f;
    }
}