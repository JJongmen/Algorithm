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
        while (true) {
            String s = scan.nextLine();
            if (".".equals(s)) return;
            int sLen = s.length();
            int aLen = sLen - failure(s)[sLen - 1];
            System.out.println(sLen % aLen == 0 ? sLen / aLen : 1);
        }
    }
}