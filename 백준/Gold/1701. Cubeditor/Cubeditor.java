import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();

        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            result = Math.max(result, getMax(str.substring(i)));
        }
        System.out.println(result);
    }

    private static int getMax(String s) {
        int[] f = new int[s.length()];
        int j = 0;
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) j = f[j - 1];
            if (s.charAt(i) == s.charAt(j)) max = Math.max(max, f[i] = ++j);
        }
        return max;
    }
}