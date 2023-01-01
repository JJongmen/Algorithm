import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[] s = scan.next().toCharArray();
        char[] bomb = scan.next().toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length; i++) {
            sb.append(s[i]);
            if (sb.length() < bomb.length) continue;
            boolean hasBomb = true;
            for (int j = 0; j < bomb.length; j++) {
                if (sb.charAt(sb.length() - bomb.length + j) == bomb[j]) continue;
                hasBomb = false;
                break;
            }
            if (!hasBomb) continue;
            sb.delete(sb.length() - bomb.length, sb.length());
        }
        System.out.println(sb.length() == 0 ? "FRULA" : sb);
    }
}